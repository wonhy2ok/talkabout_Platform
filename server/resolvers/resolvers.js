const resolvers = {
    Query: {
      chatting: () => {
        return chattingLog;
      },
    },
    Mutation: {
      write: (_, { writer, description }) => {
        const id = chattingLog.length;
        const newChat = {
          id,
          writer,
          description
        };
      chattingLog.push(newChat);
      pubsub.publish(NEW_CHAT, {
        newChat
      });
      return "YES";
      }
    },
    Subscription: {
      newChat: {
        subscribe: (_, __, { pubsub }) =>
        pubsub.asyncIterator(NEW_CHAT)
      },
    },
  };