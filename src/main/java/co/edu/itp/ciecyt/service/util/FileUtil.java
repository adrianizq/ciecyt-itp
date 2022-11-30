package co.edu.itp.ciecyt.service.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import co.edu.itp.ciecyt.service.dto.FileVO;

public class FileUtil {

	private static FileUtil file;
	private FileUtil ()
	{

	}

	public static FileUtil getInstance ()
	{
		if (file == null)
			file = new FileUtil();
		return file;
	}


	public String getSeparator(){
		return System.getProperty("file.separator");
	}

	public String setToPath(String path){
		return path.replace(getSeparator().charAt(0), '/');
	}

	public boolean acceptExtension (String fileName, String extension)
	{
		return (fileName.endsWith(extension));
	}

	public void createDirectory (String path)
			throws Exception, IllegalArgumentException, DirectoryException
			{
		StringTokenizer st = new StringTokenizer(path);
		if (st.hasMoreTokens() && st.countTokens() > 1)
			throw new IllegalArgumentException(
					"El directorio no puede contener espacios en blanco!");
		File directory = new File(path);
		if (directory.exists())
			throw new DirectoryException("El directorio que quiere crear ya existe!");
		directory.mkdirs();
		//		System.out.println("Directorio creado: "+path);

			}

	public boolean existDirectory (String directory)
	{
		File dir = new File(directory);
		if (dir == null)
			return false;
		//Borramos el directorio
		if (dir.exists())
			return true;
		else
			return false;
	}

	public boolean isDirectory (String directory)
	{
		File dir = new File(directory);
		if (dir == null)
			return false;
		//Borramos el directorio
		if (dir.isDirectory())
			return true;
		else
			return false;
	}

	public void deleteDirectory (String directory)
			throws DirectoryException{
		File dir = new File(directory);
		if (dir == null)
			throw new DirectoryException("El directorio " + directory + " no existe!");
		//Borramos todos los archivos
		String[] list = dir.list();
		File file;
		if (list != null)
		{
			for (int i = 0; i < list.length; i++)
			{
				file = new File(directory + getSeparator() + list[i]);
				if(file.exists()&& file.isDirectory())
				{
					try
					{
						deleteDirectory(file.getAbsolutePath());
					}
					catch (DirectoryException ex)
					{
					}
				}
				boolean isdeleted = file.delete();

				System.out.println("  borrado " + isdeleted);
			}
		}

		//Borramos el directorio
		if (!dir.delete())
			throw new DirectoryException("El directorio " + directory + " no se puede borrar!");
		else
			System.out.println("Directorio " + directory + " eliminado!");
	}

	public void deleteFilesInDirectory (String directory)
			throws DirectoryException{
		File dir = new File(directory);
		if (dir == null)
			throw new DirectoryException("El directorio " + directory + " no existe!");
		//Borramos todos los archivos
		String[] list = dir.list();
		File file;
		if (list != null)
		{
			for (int i = 0; i < list.length; i++)
			{
				file = new File(directory + getSeparator() + list[i]);
				boolean isdeleted = file.delete();

				System.out.println("  File: "+file.getName()+", deleted: " + isdeleted);
			}
		}


	}

	public boolean deleteFile (String nameFile)
	{
		File deleteFile = new File(nameFile);
		if (deleteFile.delete())
		{
			System.out.print(deleteFile.getName() + " archivo eliminado. ");
			return true;
		}
		else
		{
			System.out.print("Could not delete " + deleteFile.getName() + ".");
			return false;
		}
	}
	
	public void deleteFilesInDirectoryByPattern (File directory, String prefix, String extension)	throws DirectoryException{

		if (directory == null)
			throw new DirectoryException("El directorio " + directory + " no existe!");

		ExtensionNameFilter filter = new ExtensionNameFilter(prefix, extension);
		//Borramos todos los archivos
		File[] list = directory.listFiles( filter );
		File file;
		if (list != null)
		{
			for (int i = 0; i < list.length; i++)
			{
				file = list[i];
				boolean isdeleted = file.delete();

				System.out.println("  File: "+file.getName()+", deleted: " + isdeleted);
			}
		}


	}


	public boolean existsFile (String inputfile)
	{
		File existFile = new File(inputfile);
		if (existFile.exists())
		{
			System.out.print("Archivo encontrado: " + inputfile + ".");
			return true;
		}
		else
		{
			System.out.print("Archivo no encontrado: " + inputfile + ".");
			return false;
		}
	}

	public boolean isFile (String file)
	{
		File existFile = new File(file);
		if (existFile.isFile())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void renameTo (String inputfile, String outputfile)
	{
		File srcFile = new File(inputfile);
		File destFile = new File(outputfile);
		if (srcFile.renameTo(destFile))
		{
			System.out.print(srcFile.getName() + " ha sido renombrado a " + destFile.getName());
		}
		else
		{
			System.out.print(srcFile.getName() + " no se pudo renombrar a " + destFile.getName());
		}
	}
	
	public void renameTo (File srcFile, File destFile)
	{
		
		if (srcFile.renameTo(destFile))
		{
			System.out.print(srcFile.getName() + " ha sido renombrado a " + destFile.getName());
		}
		else
		{
			System.out.print(srcFile.getName() + " no se pudo renombrar a " + destFile.getName());
		}
	}
	
	public void sortByDateModifiedVO( List<FileVO> list ){
		Collections.sort( list, new DateModifiedComparatorVO());
	}
	
	public void sortByDateModified( File[] list ){
		Arrays.sort( list, new DateModifiedComparator());
	}
	
	public File getFileLastModified( File dir, String ext ) {
		File f = null;
		ExtensionFilter filter = new ExtensionFilter(ext);
		File[] listFiles = dir.listFiles(filter);
		if( listFiles != null && listFiles.length > 0 ) {
			sortByDateModified(listFiles);
			f = listFiles[0];
		}
		
		return f;
	}

	public List<FileVO> getFiles (String directory, String e)
	{
		//System.out.println( "Obteniendo lista de archivos para el directorio " + directory);
		ExtensionFilter filter = new ExtensionFilter(e);
		File dir = new File(directory);
		List<FileVO> listFiles = new ArrayList<FileVO>();
		File[] list = dir.listFiles( filter );

		if (list == null || list.length == 0)return listFiles;

		for (int i = 0; i < list.length; i++)
		{
			File file = list[i];
			FileVO f = new FileVO();
			f.setName(file.getName());
			f.setIsDirectory(file.isDirectory());
			f.setDateModified( new Date(file.lastModified()) );
			f.setFormatSize(FileUtils.byteCountToDisplaySize( file.length()));
			
			listFiles.add(f);

		}

		return listFiles;

	}
	
	public void removeFiles ( File directory, String namePattern)
	{
		//System.out.println( "Obteniendo lista de archivos para el directorio " + directory);
		NameFilter filter = new NameFilter(namePattern);
		
		
		File[] list = directory.listFiles( filter );

		if (list == null || list.length == 0)return ;

		for (int i = 0; i < list.length; i++)
		{
			File file = list[i];
			
			boolean isdeleted = file.delete();
			System.out.println("File " + file.getName()+" is deleted: " + isdeleted);

		}


	}

	public List<FileVO> getPatternFiles (String directory, String namePattern, String e)
	{
		//System.out.println( "Obteniendo lista de archivos para el directorio " + directory);
		ExtensionNameFilter filter = new ExtensionNameFilter(namePattern, e);
		File dir = new File(directory);
		List<FileVO> listFiles = new ArrayList<FileVO>();
		File[] list = dir.listFiles( filter );

		if (list == null || list.length == 0)return listFiles;

		for (int i = 0; i < list.length; i++)
		{
			File file = list[i];
			FileVO f = new FileVO();
			f.setName(file.getName());
			f.setIsDirectory(file.isDirectory());
			f.setDateModified( new Date(file.lastModified()) );
			f.setFormatSize(FileUtils.byteCountToDisplaySize( file.length()));
			listFiles.add(f);

		}

		return listFiles;

	}

	public List<FileVO> getFiles (String directory)
	{
		//System.out.println( "Obteniendo lista de archivos para el directorio " + directory);

		File dir = new File(directory);
		List<FileVO> listFiles = new ArrayList<FileVO>();
		File[] list = dir.listFiles();
		
		sortByDateModified(list);

		if (list == null || list.length == 0)return listFiles;

		for (int i = 0; i < list.length; i++)
		{
			File file = list[i];
			FileVO f = new FileVO();
			f.setName(file.getName());
			f.setIsDirectory(file.isDirectory());
			f.setDateModified( new Date(file.lastModified()) );
			f.setFormatSize(FileUtils.byteCountToDisplaySize( file.length()));
			listFiles.add(f);

		}
		return listFiles;

	}


	/**
	 * Devuelve el directorio padre de un archivo
	 * @param file Ruta del archivo
	 * @return Ruta del directorio
	 */
	public String getParent (String file)
	{
		File f = new File(file);
		String name = null;
		if(f.exists())
			name = f.getParent();
		return name;


	}

	public String removeLastToPath(String path){
		if( path != null){
			if( path.endsWith( getSeparator())){
				int index = 0;
				if( path != null){
					index = path.lastIndexOf(getSeparator());
					if( index >1 )
						path = path.substring(0, index);
				}
			}
		}

		return path;
	}
	
	public String getExtensionFile( String nameFile ){

		int index = nameFile.lastIndexOf(".");

		String nameFileInst = nameFile;
		if( index > 1){
			nameFileInst = nameFile.substring( (index ) );
		}

		return nameFileInst;
	}

	public String removeExtension(String path){
		if( path != null){

			int index = 0;
			if( path != null){
				index = path.lastIndexOf(".");
				if( index >1 )
					path = path.substring(0, index);
			}
		}

		return path;
	}
	
	/**
	 * Devuelve el nombre de un archivo dado su path absoluto
	 * @param file Path absoluto
	 * @return Nombre de archivo
	 */
	public String getNameFile (String file)
	{
		File f = new File(file);
		String name = null;
		if(f.exists())
			name = f.getName();
		return name;
	}

	/**
	 * Genera el nombre de una archivo temporal con la fecha actual.
	 * @return
	 */
	public String generateDateNameFile( String fileName ){
		String nameFile = "";
		Date d = DateUtil.getInstance().getDateCurrent();
		fileName = formatNameFile(fileName);
		String dateFormat = DateUtil.getInstance().format("yyyyMMddhhmmss", d);
		nameFile = getFormatNameFile( fileName, "_", dateFormat );
		return nameFile;
	}

	/**
	 * Genera un nombre compuesto para un nombre de archivo compuesto por un nombre y un sufijo
	 * @param nameFile. Nombre de Archivo original
	 * @param separator. Define el caracter de separaci�n entre el nombre de archivo y el sufijo
	 * @param suffix. Sufijo de composici�n para el nombre de archivo generado
	 * @return
	 */
	public String getFormatNameFile( String nameFile, String separator, String suffix ){

		int index = nameFile.lastIndexOf(".");

		String nameFileInst = nameFile;
		if( index > 1){
			nameFileInst = nameFile.substring(0,index) + separator + suffix + nameFile.substring( index );
		}

		return nameFileInst;
	}

	

	/**
	 * Remueve espacios en Blanco para el nombre del archivo
	 * @param fileName
	 * @return
	 */
	public String formatNameFile(String fileName ){
		String name = "";
		name = StringUtils.replace(fileName, " ", "_");
		return name;
	}

	

	public String generateFileName( String prefix, String ext ){
		String fileName = prefix+"_"+DateUtil.getInstance().format("yyyyMMdd_hhmmss", 
				DateUtil.getInstance().getDateCurrent())+"."+ext;

		return fileName;
	}
	
	public String formatName( String name, String replacement ){
		String formatName = "";
		formatName = StringUtils.replace(name, " ", replacement);
		return formatName;
	}

	class ExtensionFilter
	implements FilenameFilter
	{
		private String extension;
		public ExtensionFilter (String extension)
		{
			this.extension = extension;
		}

		public boolean accept (File dir, String name)
		{
			return (name.endsWith(extension));
		}
	}
	
	class NameFilter implements FilenameFilter
	{
		private String namePattern;
		public NameFilter (String pattern )
		{
			this.namePattern = pattern;
		}

		public boolean accept (File dir, String name)
		{
			return (name.startsWith(namePattern));
		}
	}

	class ExtensionNameFilter
	implements FilenameFilter
	{
		private String extension;
		private String namePattern;
		public ExtensionNameFilter (String namePattern, String extension)
		{
			this.extension = extension;
			this.namePattern = namePattern;
		}

		public boolean accept (File dir, String name)
		{
			return (name.startsWith(namePattern) && name.endsWith(extension) );
		}
	}
	
	public class DateModifiedComparatorVO implements Comparator<FileVO> {

	    public int compare(FileVO f1, FileVO f2) {
	       return f2.getDateModified().
	                compareTo(f1.getDateModified());
	                
	    }
	}
	
	public class DateModifiedComparator implements Comparator<File> {

	    public int compare(File f1, File f2) {
	    	Long l1 = f1.lastModified();
	    	Long l2 = f2.lastModified();
	       return l2.compareTo(l1);
	                
	    }
	}

	public static void main(String[] args) {
		try {
			//System.out.println(FileUtil.getInstance().setToPath("/sge/docs\\cma\\prueba.doc"));
			//System.out.println(FileUtil.getInstance().removeLastToPath("c:\\proyectos\\lince\\WebContent\\reports\\batch\\"));
			File dir = new File("/home/jltovarg/test/banner");
			File f = FileUtil.getInstance().getFileLastModified(dir, "xlsx");
			System.out.println( f.getName() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
