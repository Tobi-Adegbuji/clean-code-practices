import java.io.*;

public class TryWithResourcesOverTryFinally {
    private static final int BUFFER_SIZE = 20;

//    Historically, a try-finally statement was the best way to guarantee that a
//    resource would be closed properly, even in the face of an exception or return
    //Try-Finally gets ugly with multiple resources.

    //Example:
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }



    /*
    Try-Resources makes code cleaner and is not susceptible to code
    throwing an exception. If the close method throws in exception in the above code,
    the exception from the try block will be buried. It won't show in the logs.
    This is the exception we really want to see.

    To utilize the try-resources To be usable with this construct,
    a resource must implement the AutoCloseable interface, which consists of a
    single void-returning close method.

    Notice close method is called w/o having to declare it.
    If close method also throws exception, it will be suppressed in favor of the exception you actually
    want to see.

    Catch clause can also be added to try-with-resources as well to handle exceptions
     */

    static void copyWithryResources(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }


    /*
    The lesson is clear: Always use try-with-resources in preference to tryfinally when working with resources that must be closed. The resulting code is
    shorter and clearer, and the exceptions that it generates are more useful. The trywith-resources statement makes it easy to write correct code using resources that
    must be closed, which was practically impossible using try-finally.
     */




}
