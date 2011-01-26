/**
 * 
 */
package net.neurowork.cenatic.centraldir.web.pdf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.neurowork.cenatic.centraldir.model.indicators.Indicator;

import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 *
 */
public class ChartsPdfView extends AbstractPdfView {

	private final static Logger logger = LoggerFactory.getLogger(ChartsPdfView.class);

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("Generating Indicators in PDF");
		
		response.setHeader("Content-disposition", "attachment; filename=ReporteIndicadores.pdf");
		document.addTitle("Indicadores");
//		Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(0, 0, 128));

		// Create the Introduction chapter.
//		Paragraph title = new Paragraph ("Indicadores", titleFont);
//		title.setAlignment (Element.ALIGN_CENTER);
//		title.setSpacingAfter (18.0f);
//
//		Chapter chapter = new Chapter (title, 0);
//		chapter.setNumberDepth (0);
		
		List<Indicator> indicators = (List<Indicator>)model.get("indicadores");
		Map<Indicator, JFreeChart> chartsMap = (Map<Indicator, JFreeChart>)model.get("indMap");
		
		if(indicators != null && chartsMap != null){
			for(Indicator indicator : indicators){
				float width = indicator.getWidth();
				float height = indicator.getHeight();

				JFreeChart chart = chartsMap.get(indicator);				
				if(chart != null){
					PdfContentByte contentByte = writer.getDirectContent();
					PdfTemplate template = contentByte.createTemplate(width, height);
					Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
					Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
				 
					chart.draw(graphics2d, rectangle2d);
				 
					graphics2d.dispose();
					contentByte.addTemplate(template, 0, 0);
					document.newPage();
				}
			}
		}

//		document.add(chapter);
	}

	@Override
	protected Document newDocument() {
		Document document = new Document(PageSize.A4.rotate());
//		document.setMargins(36, 36, 54, 72);
		return document;
	}

}
