import { GraphQLServer, PubSub } from "graphql-yoga";
import Blog from './mongoose/model';
import connect from './mongoose';

connect(); // new

const pubsub = new PubSub();
const NEW_CHAT = "NEW_CHAT";

let chattingLog = [{
  id: 0,
  writer: "TalkAbout 관리자",
  description: "HELLO"
}];

/**
 * Graphql server 필수 조건1 typeDefs
 * 쿼리의 데이터 타입을 정의하는 부분.
 * !는 필수, []는 배열을 뜻함.
 */
const typeDefs = `
type Chat {
  id: Int!
  writer: String!
  description: String!
}
type Query {
  chatting: [Chat]!
}
type Mutation {
  write(writer: String!, description: String!): String!
}
type Subscription {
  newChat: Chat
}
`;
/**
 * Graphql server 필수 조건2 resolvers
 * 실제 쿼리 요청이 들어왔을 때 어떻게 수행할지 정의하는 부분
 */
const resolvers = {
    Query: {
      chatting: () => {
        return chattingLog;
      },
    },
    Mutation: {
      write: (_, { writer, description }) => {
        const id = chattingLog.length;  //채팅 로그를 쌓기 위해 채팅배열의 크기를 id로 지정. 쌓일수록 점차 증가
        const newChat = {  //채팅로그에 쌓이는 내용을 객체 타입으로 담기
          id,
          writer,
          description
        };
      chattingLog.push(newChat);  //채팅로그(배열)에 추가
      pubsub.publish(NEW_CHAT, {  //NEW_CHAT을 구독
        newChat		      //실시간 채팅내용 출력
      });
      return "YES";
      }
    },
    Subscription: {	      //NEW_CHAT
      newChat: {
        subscribe: (_, __, { pubsub }) =>
        pubsub.asyncIterator(NEW_CHAT) //이벤트가 발생할때마다 반응.
      },
    },
  };
  const server = new GraphQLServer({
    typeDefs: typeDefs,  //데이터 타입 정의
    resolvers: resolvers,  //쿼리 수행 내용 정의
    context: { pubsub }  //서버가 초기화할 때 구독내용 출력
  });
  server.start(() => console.log("Graphql Server Running"));
