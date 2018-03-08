package com.company.project.server.download;


import com.company.project.server.download.httpclient.HttpClientDownload;
import com.company.project.server.download.httpdownload.HttpDownload;
import com.company.project.server.download.selenium.SeleniumDownloader;

/**
 * Created by rock on 8/17/15.
 */
public class DownloadFactory {
	public static synchronized Download getnewDownload(Download.DownloadType type) {
		// if(type== Download.DownloadType.java_download){
		// return new HttpDownload();
		// }else
		//
		if (type == Download.DownloadType.httpclient_download) {
			return new HttpClientDownload();
		} else if (type == Download.DownloadType.chrome_download) {
			return new SeleniumDownloader();
		} else if (type == Download.DownloadType.firefox_download) {
			return new SeleniumDownloader();
		} else if (type == Download.DownloadType.openscript_download) {

		} else if (type == Download.DownloadType.phantomjs_download) {
			return new SeleniumDownloader();
		} else if (type == Download.DownloadType.jbrowser_download) {
			return new SeleniumDownloader();
		}
		return new HttpDownload();
	}
}
