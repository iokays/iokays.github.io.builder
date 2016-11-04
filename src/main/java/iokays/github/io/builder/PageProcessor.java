package iokays.github.io.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PageProcessor {

	private static final String name = "彭元彬";
	private static final String sign = "I'm not the Commonwealth.";

	public static void main(String[] args) throws IOException, URISyntaxException, TemplateException {

		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		cfg.setDirectoryForTemplateLoading(new File(PageProcessor.class.getClassLoader().getResource("").getPath()));
		Template template = cfg.getTemplate("default.ftl");

		final Map<String, List<Entity>> catalog = Maps.newLinkedHashMap();
		catalog.put("笔记", Lists.newArrayList());
		catalog.get("笔记").add(new Entity("algorithms"));
		catalog.get("笔记").add(new Entity("machineLearning"));
		catalog.get("笔记").add(new Entity("spark"));
		catalog.get("笔记").add(new Entity("scala"));
		
		catalog.put("其他", Lists.newArrayList());
		catalog.get("其他").add(new Entity("tools"));

		for (String key : catalog.keySet()) {
			for (Entity entity : catalog.get(key)) {
				final Map<String, Object> value = Maps.newHashMap();
				value.put("name", name);
				value.put("sign", sign);
				value.put("catalog", catalog);
				value.put("entity", entity);
				FileWriter writer = new FileWriter("/html/" + entity.getName() + ".html");
				template.process(value, writer);
			}
		}

	}
}
