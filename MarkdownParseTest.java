import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/*
javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
*/

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testFile() throws IOException {
        Path fileName = Path.of("broken-image-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("thereallink.com"), links);
    }

    @Test
    public void testImageFile() throws IOException {
        Path fileName = Path.of("test-image-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("thereallink.com"), links);
    }

    @Test public void testSnippet1() throws IOException {
        Path fileName = Path.of("Report-4-Markdown/1-snipReport.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("`google.com"), links);
    }

    @Test public void testSnippet2() throws IOException {
        Path fileName = Path.of("Report-4-Markdown/2-snipReport.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("a.com", "a.com(())", "example.com"), links);
    }

    @Test public void testSnippet3() throws IOException {
        Path fileName = Path.of("Report-4-Markdown/3-snipReport.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedu", "https://cse.ucsd.edu/"), links);
    }
}