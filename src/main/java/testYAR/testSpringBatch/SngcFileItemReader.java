package testYAR.testSpringBatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import testYAR.testSpringBatch.model.sngc.SngcMirTrim;

//@Component("foo-reader")
@Scope("step")
public class SngcFileItemReader implements ItemStreamReader<SngcMirTrim> {

	private static final String BODY = "DDF*";
	private static final String HEADER = "MIR*";
	// private static final Logger logger = LoggerFactory.getLogger(OrderReaderStep2.class);
	private SingleItemPeekableItemReader<FieldSet> delegate;

	// @Value("#{jobParameters['inputFileName']}")
	private String inputFileName;

	@Value("#{jobParameters['inputFileName']}")
	public void setInputFileName(final String name) {
		this.inputFileName = name;
	}

	@BeforeStep
	public void beforeStep() {

		// System.out.println("fileName : " + inputFileName);

		FlatFileItemReader fileReader = new FlatFileItemReader<>();
		fileReader.setResource(new ClassPathResource("D:\\Travail\\Dev\\Projets\\testSpringBatch2\\executions\\mir.txt"));

		final DefaultLineMapper<FieldSet> defaultLineMapper = new DefaultLineMapper<>();
		final PatternMatchingCompositeLineTokenizer orderFileTokenizer = new PatternMatchingCompositeLineTokenizer();
		final Map<String, LineTokenizer> tokenizers = new HashMap<>();
		tokenizers.put(HEADER, buildHeaderTokenizer());
		tokenizers.put(BODY, buildBodyTokenizer());
		orderFileTokenizer.setTokenizers(tokenizers);
		defaultLineMapper.setLineTokenizer(orderFileTokenizer);
		defaultLineMapper.setFieldSetMapper(new PassThroughFieldSetMapper());

		fileReader.setLineMapper(defaultLineMapper);

		delegate = new SingleItemPeekableItemReader<>();
		delegate.setDelegate(fileReader);
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}

	@Override
	public void open(ExecutionContext ec) throws ItemStreamException {
		delegate.open(ec);
	}

	@Override
	public SngcMirTrim read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// logger.info("start read");

		SngcMirTrim record = null;

		FieldSet line;
		// List<Order> bodyList = new ArrayList<>();
		while ((line = delegate.read()) != null) {
			String prefix = line.readString("typeEnregistrement");
			if (prefix.equals("DDF")) {
				record = new SngcMirTrim();
				record.setTypeEnregistrement(prefix);
			} else if (prefix.equals("MIR")) {
				record = new SngcMirTrim();
				record.setTypeEnregistrement(prefix);
				record.setCodeFonction(line.readString("codeFonction"));
				record.setNumSecuriteSociale(line.readString("numSecuriteSociale"));
			}
		}
		// logger.info("end read");
		return record;
	}

	@Override
	public void update(ExecutionContext ec) throws ItemStreamException {
		delegate.update(ec);
	}

	private LineTokenizer buildBodyTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(new Range[] { new Range(1, 3) });
		tokenizer.setNames(new String[] { "typeEnregistrement" });
		tokenizer.setStrict(false);
		return tokenizer;
	}

	private LineTokenizer buildHeaderTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

		tokenizer.setColumns(new Range[] { new Range(1, 3), new Range(4 - 4), new Range(5 - 17), new Range(18 - 47), new Range(48 - 51), new Range(52 - 52), new Range(53 - 56),
				new Range(57 - 58), new Range(59 - 60), new Range(61 - 65), new Range(66 - 69), new Range(70 - 77), new Range(78 - 78), new Range(79 - 80), new Range(92 - 92),
				new Range(93 - 108), new Range(109 - 118), new Range(121 - 140) });

		tokenizer.setNames(new String[] { "typeEnregistrement", "typeIdAssure", "numSecuriteSociale", "nomAssure", "codeFonction", "codeMaj", "annee", "codeNatureTrim",
				"nombreUniteValide", "codeRegimeValide", "numOrganismeOrigineDeclarant", "dateOrigineDeclaration", "typeUnitesValidees", "typeTitreEchange", "codeReponse",
				"numCotisantPartenaire", "prenom", "codeAnomalie" });
		tokenizer.setStrict(false);
		return tokenizer;
	}
}