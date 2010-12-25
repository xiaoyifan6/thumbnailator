package net.coobird.thumbnailator.tasks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.tasks.io.FileImageSink;
import net.coobird.thumbnailator.tasks.io.FileImageSource;

/**
 * A thumbnail generation task which reads and writes data from and to a 
 * {@link File}.
 * <p>
 * Only the first image included in the image file will be read. Subsequent
 * images included in the image file will be ignored.
 * 
 * @author coobird
 *
 */
public class FileThumbnailTask extends ThumbnailTask
{
	/**
	 * The {@link SourceSinkThumbnailTask} used to perform the task.
	 */
	private final SourceSinkThumbnailTask task;
	
	/**
	 * Creates a {@link ThumbnailTask} in which image data is read from the 
	 * specified {@link File} and is output to a specified {@link File}, using
	 * the parameters provided in the specified {@link ThumbnailParameter}.
	 * 
	 * @param param				The parameters to use to create the thumbnail.
	 * @param sourceFile		The {@link File} from which image data is read.
	 * @param destinationFile	The {@link File} to which thumbnail is written.
	 */
	public FileThumbnailTask(ThumbnailParameter param, File sourceFile, File destinationFile)
	{
		super(param);
		this.task = new SourceSinkThumbnailTask(
				param,
				new FileImageSource(sourceFile),
				new FileImageSink(destinationFile)
		);
	}

	@Override
	public BufferedImage read() throws IOException
	{
		return task.read();
	}
	
	@Override
	public void write(BufferedImage img) throws IOException
	{
		task.write(img);
	}

	@Override
	public ThumbnailParameter getParam()
	{
		return task.getParam();
	}
}
