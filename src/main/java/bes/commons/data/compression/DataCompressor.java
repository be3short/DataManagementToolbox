package bes.commons.data.compression;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

public class DataCompressor
{

	public static byte[] stringToByteCompressor(String compressable, CompressionFormat compression)
	{
		byte[] byteOut = null;
		switch (compression)
		{
		case GZIP:
			byteOut = compressDataGZip(compressable);
			break;
		default:
			byteOut = compressable.getBytes();
			break;
		}
		return byteOut;
	}

	public static byte[] compressDataGZip(String string)
	{
		byte[] dataToCompress = string.getBytes(StandardCharsets.ISO_8859_1);
		byte[] compressedData = null;
		try
		{
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(dataToCompress.length);
			try
			{
				GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
				try
				{
					zipStream.write(dataToCompress);
				} finally
				{
					zipStream.close();
				}
			} finally
			{
				byteStream.close();
			}

			compressedData = byteStream.toByteArray();
		} catch (Exception e)
		{
			e.printStackTrace();
			compressedData = string.getBytes();
		}
		return compressedData;
	}
}
