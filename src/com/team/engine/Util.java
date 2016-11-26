package com.team.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;

public class Util {
	/**
	 * Returns true if the specified file exists.
	 */
	public static boolean fileExists(String file) {
		return Files.exists(Paths.get(file), LinkOption.NOFOLLOW_LINKS);
	}
	
	public static ByteBuffer readFile(String resource) {
		ByteBuffer buffer;

		Path path = Paths.get(resource);
		if ( Files.isReadable(path) ) {
			try (SeekableByteChannel fc = Files.newByteChannel(path)) {
				buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);
				while ( fc.read(buffer) != -1 ) ;
				
				buffer.flip();
				return buffer;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static String readFileString(String resource) {
		String source = "";
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(Settings.RESOURCE_PATH + resource), Charset.defaultCharset());
			String line = null;
			while ((line = reader.readLine()) != null) {
				source += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return source;
	}
}
