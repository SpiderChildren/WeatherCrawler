public class PictureNotExitException extends Exception{

    private  String url;
    private  String name;

    public PictureNotExitException()
    {
        super();
    }

    public PictureNotExitException(String mesg , String url , String name)
    {
        super(mesg);
        this.name = name;
        this.url = url;
    }

    public String getUrl()
    {
        return this.url;
    }

    public String getName()
    {
        return this.name;
    }
}
