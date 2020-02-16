package project.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import project.dao.impl.GroupDaoImpl;
import project.dao.impl.UserDaoImpl;
import project.model.Message;
import project.database.DatabaseConnection;

public class SaveChat {
    public void SaveThisMessage(String sender, String receiver, String content) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.initializeDatabase();
        Statement state = conn.createStatement();
        System.out.println("content: "+content);
        System.out.println(sender);
        System.out.println(receiver);
        String sql = 
                "USE PROJECT1;\n" +
                "DECLARE @USERID1 AS NUMERIC(18,0);\n" +
                "DECLARE @USERID2 AS NUMERIC(18,0);\n" +
                "DECLARE @CHATID AS NUMERIC(18,0);\n" +
                "SET @USERID1 = (SELECT ID FROM USERINF WHERE USERNAME = '"+sender+"');\n" +
                "SET @USERID2 = (SELECT ID FROM USERINF WHERE USERNAME = '"+receiver+"');\n" +
                "SET @CHATID = (SELECT ID FROM CONVERSATIONS \n" +
                "	WHERE (USERID1 = @USERID1 AND USERID2 = @USERID2)\n" +
                "		OR (USERID1 = @USERID2 AND USERID2 = @USERID1));\n" +
                "IF (@CHATID IS NULL)\n" +
                "	BEGIN\n" +
                "		INSERT INTO CONVERSATIONS(USERID1, USERID2, TIMEVALUE) VALUES\n" +
                "			(@USERID1,@USERID2,GETDATE());\n" +
                "		DECLARE @NEWID AS NUMERIC(18,0);\n" +
                "		SET @NEWID = (SELECT ID FROM CONVERSATIONS WHERE USERID1 = @USERID1 and USERID2 = @USERID2);\n" +
                "		INSERT INTO CHATSENTENCE VALUES\n" +
                "			(@NEWID,@USERID1,N'"+content+"',GETDATE());	\n" +
                "	END\n" +
                "ELSE \n" +
                "	INSERT INTO CHATSENTENCE VALUES\n" +
                "		(@CHATID,@USERID1,N'"+content+"',GETDATE());";
        state.executeUpdate(sql);
}
    public void saveThisMessageGroup(Message message) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseConnection.initializeDatabase();
        Statement state = conn.createStatement();
        GroupDaoImpl groupDaoImpl = new GroupDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String groupId = groupDaoImpl.getGroupInfoByName(message.getTo()).getId();
        String userId = userDaoImpl.getInfoByUsername(message.getFrom()).getId();
        String sql = 
                "USE PROJECT1;\n" +
                "INSERT INTO ROOMMESSAGE VALUES\n" +
                "	('"+groupId+"','"+userId+"','"+message.getContent()+"',GETDATE());";
        state.executeUpdate(sql);
        state.close();
        conn.close();
    }
}
