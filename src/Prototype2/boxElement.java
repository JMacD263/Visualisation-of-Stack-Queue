package src.Prototype2;

/**
 * Created by xnb12162 on 03/02/16.
 */
public class boxElement {
    int elementNo;

    public boxElement(int element){
        elementNo = element;
    }
    
    public String getText(){
        return Integer.toString(elementNo);
    }
}
