package ilion.util.lucene;
import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.ASCIIFoldingFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class MyCustomAnalyzer extends Analyzer {

	@Override
	 public TokenStream reusableTokenStream(
		        String fieldName, Reader reader) throws IOException {

		        SavedStreams streams =
		            (SavedStreams) getPreviousTokenStream();

		        if (streams == null) {
		            streams = new SavedStreams();
		            setPreviousTokenStream(streams);

		            streams.tokenizer = new StandardTokenizer(LuceneConfig.VERSAO_LUCENE,reader);
		            streams.stream = new ASCIIFoldingFilter(streams.tokenizer);
		            streams.stream = new StandardFilter(streams.stream);
		            streams.stream = new LowerCaseFilter(streams.stream);

		        } else {
		            streams.tokenizer.reset(reader);
		        }

		        return streams.stream;
		    }

	   private class SavedStreams {
	        Tokenizer tokenizer;
	        TokenStream stream;
	    }

	@Override
	public TokenStream tokenStream(String feildName, Reader reader) {
		Tokenizer tokenizer = new StandardTokenizer(Version.LUCENE_29,reader);
		TokenStream stream = new ASCIIFoldingFilter(tokenizer);
		stream = new StandardFilter(stream);
        stream = new LowerCaseFilter(stream);

		return stream;
	}
}