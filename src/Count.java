import javax.swing.JButton;

public class Count {
static int count(JButton a[][], String s)
{int ct=0;
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
		{
			if(a[i][j].getName().compareTo(s)==0)
			{
				ct++;
			}
		}
		
	}

	return ct; 
}

static boolean pass(JButton a[][])
{boolean c=false;
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
		{
			if(a[i][j].getName().compareTo("LEL")==0)
			{
				c=true;
			}
		}
		
	}
	return c;
}
}
