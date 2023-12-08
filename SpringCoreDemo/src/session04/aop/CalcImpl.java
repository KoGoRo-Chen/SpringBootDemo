package session04.aop;

import org.springframework.stereotype.Component;

@Component("calcImpl")  //Component預設名稱是class名稱變小寫，若沒要另外取名的話可不用寫
public class CalcImpl implements Calc{

	@Override
	public Integer add(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return x + y;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return x / y;
	}

}
