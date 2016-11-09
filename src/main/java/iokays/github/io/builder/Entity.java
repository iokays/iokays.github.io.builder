package iokays.github.io.builder;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.pegdown.PegDownProcessor;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Entity  implements Serializable {
	
	private static final Map<String, String> H = Maps.newLinkedHashMap();
	{
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

	private final String title;
	private final String subTitle;
	private final String name;
	
	private String content;
	private final List<String> keywords;

	public Entity(final String name) throws IOException, URISyntaxException {
		this.name = name;
		final List<String> temp = Files.readAllLines(Paths.get(IokayPath.IN + File.separator + name + ".md"), Charsets.UTF_8);
		//取第一行标题
		title = temp.get(0).substring(temp.get(0).indexOf("<!--") + 4, temp.get(0).indexOf("-->")).trim();
		subTitle = temp.get(1).substring(temp.get(1).indexOf("<!--") + 4, temp.get(1).indexOf("-->")).trim();
		
		content = new PegDownProcessor().markdownToHtml(Joiner.on("\n").join(temp));
		keywords = Lists.newArrayList(title);
		
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
						if (null != keyword && keyword.length() != 0 
								&& (key.equals("<h>") || key.equals("<h1>") || key.equals("<h2>") || key.equals("<h3>") || key.equals("<h4>"))) {
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

	public String getSubTitle() {
		return subTitle;
	}

	public String getContent() {
		return content;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", title=" + title + ", content=" + content + ", keywords=" + keywords + "]";
	}

}
