package iokays.github.io.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PageProcessor {

	private static final String name = "彭元彬。";
	private static final String sign = "I'm not the Commonwealth.";

	public static void main(String[] args) throws IOException, URISyntaxException, TemplateException {
		run(args);
	}

	private static final void run(String[] args) throws IOException, URISyntaxException, TemplateException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		cfg.setDirectoryForTemplateLoading(new File(IokayPath.IN));
		Template template = cfg.getTemplate("default.ftl", "utf-8");

		final Map<String, List<Entity>> catalog = Maps.newLinkedHashMap();
		final byte[] catalogJson = Files.readAllBytes(Paths.get(IokayPath.IN + File.separator + "catalog.json"));
		Map<String, List<String>> map = new Gson().fromJson(new String(catalogJson, Charsets.UTF_8),
				new TypeToken<LinkedHashMap<String, List<String>>>() {
				}.getType());

		for (String key : map.keySet()) {
			final List<Entity> entities = Lists.newArrayList();
			for (String _name : map.get(key)) {
				entities.add(new Entity(_name));
			}
			catalog.put(key, entities);
		}
		
		OutputStreamWriter out = null;
		for (String key : catalog.keySet()) {
			for (Entity entity : catalog.get(key)) {
				final Map<String, Object> value = Maps.newHashMap();
				value.put("name", name);
				value.put("sign", sign);
				value.put("catalog", catalog);
				value.put("entity", entity);
				
				out = new OutputStreamWriter(new FileOutputStream(IokayPath.OUT + File.separator + entity.getName() + ".html"),"UTF-8"); 
				template.process(value, out);
				out.flush(); 
	            out.close();
	            System.out.println(entity.getName());
	            
	            //首页生成
	            if (entity.getName().equals("algorithms")) {
	            	final Template indexTemplate = cfg.getTemplate("index.ftl", "utf-8");
	            	out = new OutputStreamWriter(new FileOutputStream(IokayPath.OUT + File.separator + "index.html"),"UTF-8"); 
	            	indexTemplate.process(value, out);
	        		out.flush(); 
	                out.close();
	                System.out.println("index");
	            }
			}
		}
		
		
	}
}
