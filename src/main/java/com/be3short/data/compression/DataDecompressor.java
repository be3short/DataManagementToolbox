package com.be3short.data.compression;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.be3short.data.file.general.FileSystemInteractor;

public class DataDecompressor
{

	public static String decompressFileContentsToString(String directory, String file_name)
	{
		File file = new File(directory, file_name);
		CompressionFormat compression = CompressionFormat.getFormat(file_name);
		return decompressFileContentsToString(file, compression);
	}

	public static String decompressFileContentsToString(File file, CompressionFormat compression)
	{
		String decompressed = null;
		byte[] byteContent = null;
		switch (compression)
		{
		case GZIP:
			decompressed = decompressDataGZip(file);
			break;
		default:

			break;
		}
		return decompressed;
	}

	public static String decompressDataGZip(File file)
	{
		String fileContent = "";
		try
		{
			InputStream input = new BufferedInputStream(new FileInputStream(file));// ByteArrayInputStream(compressed.getBytes()));
			GZIPInputStream gzis = new GZIPInputStream(input);
			InputStreamReader reader = new InputStreamReader(gzis, StandardCharsets.ISO_8859_1);
			BufferedReader in = new BufferedReader(reader);

			String readed;
			while ((readed = in.readLine()) != null)
			{

				fileContent += readed;
			}
			System.out.println(fileContent);
		} catch (Exception badFile)
		{
			badFile.printStackTrace();
		}
		return fileContent;
	}
}
