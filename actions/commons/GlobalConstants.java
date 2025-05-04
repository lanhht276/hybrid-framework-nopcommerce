package commons;

import java.io.File;

public class GlobalConstants {
	public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles"
			+ File.separator;
	public static final String BROWSER_EXTENSION = RELATIVE_PROJECT_PATH + File.separator + "browserExtensions"
			+ File.separator;
	public static final String DRAG_DROP_HTML5 = RELATIVE_PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = RELATIVE_PROJECT_PATH + File.separator + "autoIT";
	public static final long LONG_TIMEOUT = 30;
	public static final long SHORT_TIMEOUT = 5;
	public static final long RETRY_TEST_FAIL = 3;
}
