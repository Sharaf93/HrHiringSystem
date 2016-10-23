/**
 * 
 */
package pagecode;

import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlDataTable;

/**
 * @author fatma
 *
 */
public class PhasesTable extends PageCodeBase {

	protected UIColumn col;
	protected HtmlDataTable candidateDataTable;

	protected UIColumn getCol() {
		if (col == null) {
			col = (UIColumn) findComponentInRoot("col");
		}
		return col;
	}

	protected HtmlDataTable getCandidateDataTable() {
		if (candidateDataTable == null) {
			candidateDataTable = (HtmlDataTable) findComponentInRoot("candidateDataTable");
		}
		return candidateDataTable;
	}

}