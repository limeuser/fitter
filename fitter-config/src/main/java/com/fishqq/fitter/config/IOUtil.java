package com.fishqq.fitter.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class IOUtil {
    public static String toString(InputStream input, Charset charset) throws IOException {
        try (StringWriter sw = new StringWriter()) {
            final InputStreamReader reader = new InputStreamReader(input, charset);
            char[] buffer = new char[2048];
            int n;
            while (-1 != (n = reader.read(buffer))) {
                sw.write(buffer, 0, n);
            }
            return sw.toString();
        }
    }
}
