package iokays.github.io.builder;

import java.io.File;

public interface IokayPath {
	public static final String IN = System.getProperty("user.dir") + File.separator + "note";
	public static final String OUT = System.getProperty("user.dir");
}
