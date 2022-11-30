package co.edu.itp.ciecyt.service.dto;


import java.util.Date;

/**
 * Clase para representar un Bean de un archivo 
 * @author Jos� Luis TOVAR
 *
 */
public class FileVO
{
	private String name;
	private boolean isDirectory;
	private Date dateModified;
	private String formatSize;


	public FileVO (){}


	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean isDirectory()
	{
		return isDirectory;
	}
	public void setIsDirectory(boolean isDirectory)
	{
		this.isDirectory = isDirectory;
	}

	public String toString()
	{
		return name;
	}


	public Date getDateModified() {
		return dateModified;
	}


	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}


	/**
	 * @return the formatSize
	 */
	public String getFormatSize() {
		return formatSize;
	}


	/**
	 * @param formatSize the formatSize to set
	 */
	public void setFormatSize(String formatSize) {
		this.formatSize = formatSize;
	}
}

