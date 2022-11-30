package co.edu.itp.ciecyt.service.util;

public class DirectoryException extends Exception
{

  /**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

public DirectoryException()
  {
  }

  public DirectoryException(String message)
  {
    super(message);
  }

  public DirectoryException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public DirectoryException(Throwable cause)
  {
    super(cause);
  }
}