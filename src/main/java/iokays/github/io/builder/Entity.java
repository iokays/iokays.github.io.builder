package iokays.github.io.builder;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.pegdown.PegDownProcessor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Entity  implements Serializable {
	
	private static final Map<String, String> H = Maps.newLinkedHashMap();
	{
		H.put("<h>", "</h>");
		H.put("<h1>", "</h1>");
		H.put("<h2>", "</h2>");
		H.put("<h3>", "</h3>");
		H.put("<h4>", "</h4>");
		H.put("<h5>", "</h5>");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String name;
	private final String title;
	private String content;
	private final List<String> keywords;

	public Entity(final String name) throws IOException, URISyntaxException {
		this.name = name;
		
		final URL url = this.getClass().getClassLoader().getResource(name + ".md");
		final byte[] temp = Files.readAllBytes(Paths.get(url.toURI()));
		final String md = temp != null ? new String(temp, "utf-8") : "";
		content = new PegDownProcessor().markdownToHtml(md);
		keywords = Lists.newArrayList();
		
		//提取关键字
		for (String key : H.keySet()) {
			String str = content;
			while (true) {
				if (null == str || str.trim().length() == 0) {
					break;
				}
				final int beginIndex = str.indexOf(key);
				if (beginIndex != -1) {
					final int endIndex = str.indexOf(H.get(key));
					if (endIndex != -1) {
						final String keyword = str.substring(beginIndex + key.length(), endIndex).trim();
						if (null != keyword && keyword.length() != 0) {
							keywords.add(keyword);
						}
						str = str.substring(endIndex + H.get(key).length());
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
		
		title = keywords.isEmpty() ? "" : keywords.get(0);	//标题
		
		content = content.replaceAll("<h2>", "<h2 class=\"ui dividing header\" >");
		content = content.replaceAll("<h3>", "<h3 class=\"ui dividing header\" >");
		content = content.replaceAll("<h4>", "<h4 class=\"ui dividing header\" >");
		
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public List<String> getKeywords() {
		return keywords;
	}

}