package com.inhatc.talkabout;

import com.inhatc.talkabout.common.service.CommonService;
import com.inhatc.talkabout.common.vo.ChatVo;
import com.inhatc.talkabout.common.vo.CommonVo;
import com.inhatc.talkabout.common.vo.KeywordVo;
import com.inhatc.talkabout.common.vo.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class TalkaboutApplication {
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	CommonService commonService;
	@Resource
	private UserInfo userInfo;

	private static final Logger Logger = LogManager.getLogger(TalkaboutApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TalkaboutApplication.class, args);
	}

	/**
	 * 메일 인증
	 * @param userData {userName:유저명, userEmail:유저 이메일, userPass:유저비밀번호,
	 *                    userNick:닉네임, userBirth:생년월일, userContact:연락처}
	 * @return url of next step
	 */
	@PostMapping("/api/insUserCtfInfo")
	public String insUserCtfInfo(@RequestBody Map<String, String> userData) throws NoSuchAlgorithmException {
		String resMessage = "";
		//테이블 테스트
		//List<CommonVo> lstUser = commonService.selectTest();
		userData.put("userPass",sha256(userData.get("userPass")).replaceAll(" ",""));
		userData.put("scuPass",sha256(userData.get("userEmail")).replaceAll(" ",""));
		Logger.info("사용자 명 : " + userData.get("userName") + ", 사용자 이메일 : " + userData.get("userEmail"));

		//사용자 존재 여부 확인
		if(commonService.srhUser1(userData)){		//계정이 있는경우
			if(commonService.srhUser2(userData)) {    //인증 정보가 있는경우
				resMessage = "stat01";
				return resMessage;
			}else {	//인증 정보가 없는경우 update
				commonService.udtScuUser(userData);
			}
		}else {	//계정 정보가 없는 경우. INSERT
			commonService.addScuUser(userData);
		}
		try{
			//이메일 보내기
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("cjswo7119@naver.com");		//보내는 사람
			message.setTo(userData.get("userEmail"));	//받는 사람
			message.setSubject(userData.get("userName") + "님 지금 바로 TalkAbout");	//메일 제목
			String contents = userData.get("userName") + "님, 가입을 위해 다음 url에 접속해주세요\n " +
					"url: http://localhost:3000/join2?userPass=" +
					sha256(userData.get("userEmail")).replaceAll(" ","");
			message.setText(contents);					//메일 내용
			javaMailSender.send(message);				//메일 발송
			resMessage = "stat02";
		}catch(Exception e){
			resMessage = "stat03";
			e.printStackTrace();
		}

		return resMessage;
	}

	/**
	 * 사용자 인증 후 로그인 권한 부여
	 * @param userData {userPass:유저 메일 인증 코드}
	 * @return
	 */
	@PostMapping("/api/chkUserPass")
	public Object chkUserPass(@RequestBody Map<String, String> userData) {
		//인증 수행
		List<CommonVo> lstUser = commonService.chkUserPass(userData);
		//사용자 정보 조회
		if (!lstUser.isEmpty()) {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userState", "US02");	//로그인 권한
			paramMap.put("userId", lstUser.get(0).getUserId());	//사용자 ID(& 이메일)
			commonService.udtUserAuth(paramMap);
		}
		return lstUser;
	}

	//로그인
	@PostMapping("/api/loginUser")
	public Object login(@RequestBody Map<String, String> userData, HttpSession session) throws NoSuchAlgorithmException {
		userData.put("userPass",sha256(userData.get("userPass")).replaceAll(" ",""));
		if(commonService.srhUser1(userData)){		//이메일 존재여부 확인
			if(commonService.srhUser2(userData)) {    //인증 정보가 있는경우
				userInfo = commonService.loginUser(userData);
				session.setAttribute("userInfo", userInfo);
				if(userInfo == null){
					return "ERR02";	//비밀번호를 확인해주세요.
				}else {
					return userInfo;
				}
			}else {
				return "ERR03";		//인증을 진행해주세요.
			}
		}else {
			return "ERR01";			//없는 사용자 계정입니다.
		}
	}

	//관심단어, 연관단어 출력
	@PostMapping("/api/srchTitle")
	public Object srchTitle() throws NoSuchAlgorithmException {
		List<ChatVo> srchTitle = commonService.srchTitle();
		return srchTitle;
	}

	//단어 랭킹
	@PostMapping("/api/analGraph")
	public Object analGraph() throws NoSuchAlgorithmException {
		List<KeywordVo> analGraph = commonService.analGraph();
		return analGraph;
	}

	//유저 정보 조회
	@PostMapping("/api/srhUserInfo")
	public Object srhUserInfo(@RequestBody Map<String, String> userData) throws NoSuchAlgorithmException {
		List<CommonVo> srhUserInfo = commonService.srhUserInfo(userData);
		return srhUserInfo;
	}

	//유저 정보 수정
	@PostMapping("/api/udtUserInfo")
	public Object udtUserInfo(@RequestBody Map<String, String> userData) throws NoSuchAlgorithmException {
		//사용자 정보 수정 쿼리
		int resUdt = commonService.udtUserInfo(userData);
		System.out.println(resUdt);
		return resUdt;
	}

	//유저 비밀번호 수정
	@PostMapping("/api/UdtUserPass")
	public Object UdtUserPass(@RequestBody Map<String, String> userData) throws NoSuchAlgorithmException {
		//비밀번호 (sha256)암호화
		userData.put("userPass",sha256(userData.get("userPass")).replaceAll(" ",""));
		System.out.println(userData);
		//사용자 정보 수정 쿼리
		int resUdt = commonService.UdtUserPass(userData);
		System.out.println(resUdt);
		return resUdt;
	}

	//sha256 암호화
	public static String sha256(String msg)  throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());
		return byteToHexString(md.digest());
	}
	//바이트 단위 헥사코드 문자열 변환
	private static String byteToHexString(byte[] digest) {
		StringBuilder builder = new StringBuilder();
		for(byte b: digest){
			builder.append(String.format("%2x", b));
		}
		return builder.toString();
	}

}
