package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.FileAppender;

public class DatedFileAppender extends FileAppender{
	private String datePattern = "ssmmHHddMMyyyy";
	
	@Override
	public void setFile(String strFile) {
		String fileName = strFile;
		String strDate;
		SimpleDateFormat sdf;
 
	    if (datePattern!=null && strFile!=null) {
	        sdf = new SimpleDateFormat(datePattern);
	        strDate = sdf.format(new Date());
	        fileName = strFile.replaceAll("%date%", strDate);
	    }
	    
	    super.setFile(fileName);
	}
}
