package org.com.iot.mongoDAO;

	
import java.rmi.UnknownHostException;

import org.com.iot.common.StaticParameters;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;

/**
 * @see <P>MongoDb pool
 * @author Mickle
 * @date 29 Jul 2017
 */
public class MongoManager {

    private final static String HOST = StaticParameters.DB_HOST;// 端口  
    private final static int PORT = StaticParameters.DB_PORT;// 端口  
    private final static int POOLSIZE = StaticParameters.DB_POOLSIZE;// 连接数量  
    private final static int BLOCKSIZE = StaticParameters.DB_BLOCKSIZE; // 等待队列长度  
    private static Mongo mongo = null;  
  
    private MongoManager() { }  
  
    static {  
        try {
			initDBPrompties();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
  
    public static DB getDB() {  
        return mongo.getDB(StaticParameters.DB_NAME);  
    }  
  
    /** 
     * 初始化连接池 
     */  
    private static void initDBPrompties() throws UnknownHostException {  
        // 其他参数根据实际情况进行添加  
        try {  
            mongo = new Mongo(HOST, PORT);  
            MongoOptions opt = mongo.getMongoOptions();  
            opt.connectionsPerHost = POOLSIZE;  
            opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;  
        } catch (MongoException e) {  
  
        }  
  
    }  
}
