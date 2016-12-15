package code.model;

/**
 * Created by vadim on 16.12.16.
 */
public class WorkAroundPair
{
    private Integer num;
    private String  msg;

    WorkAroundPair(Integer _num, String _msg)
    {
        num = _num;
        msg = _msg;
    }

    public Integer getNum() {return num;}
    public String getMsg() {return msg;}

    public void setNum(Integer _num) { num = _num;}
    public void setMsg(String _msg) { msg = _msg;}
}

