package learn.springboot.springboothelloworld.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;

public class CreateIndex {

    private static final Logger logger = LoggerFactory.getLogger(CreateIndex.class);

    public static void main(String[] args) {
        //createIndex();
        read();
    }

    public static void createIndex() {
        try {
            String filePath = System.getProperty("user.home")+"/lucene/file_path/";//文件路径
            String indexPath = System.getProperty("user.home")+"/lucene/index_path/";//索引路径
            File fileDir = new File(filePath);
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer luceneAnalyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(luceneAnalyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter indexWriter = new IndexWriter(dir,iwc);
            File[] textFiles = fileDir.listFiles();

            for (int i = 0; i < textFiles.length; i++) {
                if (textFiles[i].isFile()) {
                    String temp = FileReaderAll(textFiles[i].getCanonicalPath(),
                            "GBK");
                    Document document = new Document();
                    Field FieldPath = new StringField("path", textFiles[i].getPath(), Field.Store.YES);
                    Field FieldBody = new TextField("body", temp, Field.Store.YES);
                    document.add(FieldPath);
                    document.add(FieldBody);
                    indexWriter.addDocument(document);
                }
            }
            indexWriter.close();
        } catch(Exception e) {

        }
    }

    public static void read() {
        try {
            String indexPath=System.getProperty("user.home")+"/lucene/index_path/";//索引路径
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
            IndexSearcher searcher=new IndexSearcher(reader);
            ScoreDoc[] hits=null;
            String queryString="i";//关键字符串
            Query query=null;
            Analyzer analyzer= new StandardAnalyzer();
            QueryParser qp=new QueryParser("body",analyzer);
            query=qp.parse(queryString);
            if (searcher!=null) {
                TopDocs results=searcher.search(query, 10);
                hits=results.scoreDocs;
                Document document=null;
                for (int i = 0; i < hits.length; i++) {
                    document=searcher.doc(hits[i].doc);
                    String body=document.get("body");
                    String path=document.get("path");
                    String modifiedtime=document.get("modifiField");
                    logger.info("body = {} | path={} | modifiedtime={}", body, path, modifiedtime);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public   static  String FileReaderAll(String FileName, String charset)
            throws IOException {
        BufferedReader reader  =   new  BufferedReader( new InputStreamReader(
                new FileInputStream(FileName), charset));
        String line  =   new  String();
        String temp  =   new  String();

        while  ((line  =  reader.readLine())  !=   null )   {
            temp  +=  line + "\n";
        }
        reader.close();
        return  temp;
    }



}
