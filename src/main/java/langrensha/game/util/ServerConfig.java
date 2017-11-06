package langrensha.game.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ServerConfig {
	
	static String DEFAULT_LOCAL_PATH = System.getProperty("user.dir").concat(File.separator.concat(
			"src".concat(File.separator).concat("main").concat(File.separator).concat("resources").concat(File.separator).concat("server.properties")));
	
	public static Properties getProp() {
		Properties p = new Properties();
		try {
			p.load(new FileReader(new File(DEFAULT_LOCAL_PATH)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public static void main(String[] args) {
		System.out.println(DEFAULT_LOCAL_PATH);
		Properties properties = ServerConfig.getProp();
		System.out.println(properties.getProperty("server.port"));
	}
}
