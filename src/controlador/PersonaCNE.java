package controlador;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class PersonaCNE {

    private String fullName, gender, board;
    private boolean foreign;
    private PrecinctCNE precinct;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public boolean isForeign() {
        return foreign;
    }

    public void setForeign(boolean foreign) {
        this.foreign = foreign;
    }

    public PrecinctCNE getPrecinct() {
        return precinct;
    }

    public void setPrecinct(PrecinctCNE precinct) {
        this.precinct = precinct;
    }

}
