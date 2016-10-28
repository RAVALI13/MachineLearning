//package Assignment_1_RN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class matrix_form{
	
	public 
	int[][] matrix;
	int row_count;
	int col_count;
	String[] top_header;
	String filename;
	matrix_form(String filename)
	{
		this.filename=filename;
		try{
			this.function_to_form_matrix();
		}
		catch(Exception e){
			
		}
		
	}
			
	void function_to_form_matrix()throws Exception{
		
	
		File f=new File(this.filename);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int row_count=0;
		int col_count=0;
		br = new BufferedReader(new FileReader(f));
		String temp_store;
		temp_store="";
		while ((line = br.readLine()) != null)
		{
			
			row_count=row_count+1;
			temp_store=line;
		}
		String[] my_column_array=temp_store.split(cvsSplitBy);
		col_count=my_column_array.length;
		int matrix[][]=new int[row_count+1][col_count+1];
		int i_row=0;
		int j_col=0;
		br = new BufferedReader(new FileReader(f));
		boolean flag_first=true;
		String top_header[]=null;
		while ((line = br.readLine()) != null)
		{
			if(flag_first){
				//top header includes the attribute names
				top_header=line.split(cvsSplitBy);
				flag_first=false;
				continue;
				
			}
			//reading only the attribute values
			my_column_array=line.split(cvsSplitBy);
			while (j_col<col_count) {
				matrix[i_row][j_col]=Integer.parseInt(my_column_array[j_col]);
				//System.out.print(matrix[i_row][j_col]);
				j_col=j_col+1;
		}
			j_col=0;
			i_row=i_row+1;
			//System.out.println(" ");
		}
		br.close();
		this.matrix=matrix;
		this.row_count=row_count;
		this.col_count=col_count;
		this.top_header=top_header;
	}
}

class node{
	
	public
	String name;
    node nodes[]=new node[2];
    node prev;
    String parent_value;
    int leaf_or_not;
    int positive_count;
    int negative_count;
    String parent;
	node(){
		name=null;
		nodes[0]=null;
		nodes[1]=null;
		prev=null;
		parent_value=null;
		leaf_or_not=0;
		positive_count=0;
		negative_count=0;
		parent=null;
	}
	node add(){
		node temp_node=new node();
		 return temp_node;
	}
	void traverse()
	{
		System.out.println(this.name+" leaf of "+this.parent+" traversed "+this.parent_value);
		if(this.nodes[0]!=null){
			nodes[0].traverse();
		}
        if(this.nodes[1]!=null){
			nodes[1].traverse();
		}
	}
	void treecopy(node tree_copy)
	{
		
		tree_copy.name=this.name;
		tree_copy.negative_count=this.negative_count;
		tree_copy.leaf_or_not=this.leaf_or_not;
		tree_copy.parent_value=this.parent_value;
		tree_copy.parent=this.parent;
		tree_copy.positive_count=this.positive_count;
		tree_copy.prev=this.prev;
		for(int i=0;i<=1;i++){
			
			if(this.nodes[i]!=null)
			{
				node temp=new node();
				tree_copy.nodes[i]=temp;
				
				this.nodes[i].treecopy(temp);
			}
		}
	}
	void formstach(List stack)
	{
		//System.out.println(this.name+" leaf of "+this.parent+" traversed "+this.parent_value);
		if(this.nodes[0]!=null){
			if(this.leaf_or_not!=-1 ){
				//System.out.println("adding "+this.name);
				stack.add(this);
			}
			nodes[0].formstach(stack);
		}
        if(this.nodes[1]!=null){
        	if(this.leaf_or_not!=-1){
        		//System.out.println("adding "+this.name);
				stack.add(this);
			}
			nodes[1].formstach(stack);
		}
	}
    int accuracy(int[][] matrix,int row,int column,String[] header_column)
	{
    	if(this.leaf_or_not==-1){
    		//System.out.println(this.name+" "+this.leaf_or_not+" "+this.parent);
    		if(matrix[row][column]==Integer.parseInt(this.name)){
    			//System.out.println("positive"+" "+matrix[row][column]+" "+column+" "+this.name);
    			return 1;
    		}
    		else
    		{
    			//System.out.println("negative"+" "+matrix[row][column]+" "+column+" "+this.name);
    			return 0;
    		}
    	}
    	
    	int index_1=Arrays.asList(header_column).indexOf(this.name);
    	if(index_1==-1)
    		return 0;
    	int index = Arrays.binarySearch(header_column, this.name);
    	//System.out.println("header_column_length"+header_column.length);
    	for(int i=0;i<header_column.length;i++){
    		if(header_column[i].equals(this.name)){
    			index=i;
    			break;
    		}
    	}
    	int find_node_index=matrix[row][index];
    	node find_node=this.nodes[find_node_index];
    	//System.out.println("values came and passing   "+this.name+" "+index+" "+index_1+" "+matrix[row][index]+" "+row);
    	int return_value=find_node.accuracy(matrix, row, column, header_column);
    	return return_value;
		
	}
}

public class InputRead {

	public 
	static String header_rows[]=null;
	public void recursive_matrix_variance(node tree,int[][] matrix,int head_node,int total_rows,int total_col,String[] top_header){
		
		int class_column=total_col-1;
		int zero_count=0;
		int one_count=0;
		for(int i=0;i<total_rows-1;i++){
			
			if(matrix[i][head_node]==0)
			{
				zero_count=zero_count+1;
			}
			if(matrix[i][head_node]==1)
			{
				one_count=one_count+1;
			}
			
		}
		//System.out.println(zero_count+" "+total_col);
		//System.out.println(one_count+" "+total_col);
		int temp_matrix_zero[][]=new int[zero_count][total_col-1];
		int temp_matrix_one[][]=new int[one_count][total_col-1];
		int temp_zero_i=0;
		int temp_zero_j=0;
		int temp_one_i=0;
		int temp_one_j=0;
		boolean zero_flag=false;
		boolean one_flag=true;
		String zero_top_header[]=new String[total_col-1];
		String one_top_header[]=new String[total_col-1];
		for(int j=0;j<total_col;j++){
			
			if(j==head_node){
				continue;
			}
			for(int i=0;i<total_rows-1;i++)
			{
				if(matrix[i][head_node]==0){
					temp_matrix_zero[temp_zero_i][temp_zero_j]=matrix[i][j];
					temp_zero_i=temp_zero_i+1;
					zero_flag=true;
					zero_top_header[temp_zero_j]=top_header[j];
				}
                if(matrix[i][head_node]==1){
                	temp_matrix_one[temp_one_i][temp_one_j]=matrix[i][j];
					temp_one_i=temp_one_i+1;
					one_flag=true;
					one_top_header[temp_one_j]=top_header[j];
				}
				
			}
			if(zero_flag){
				temp_zero_j=temp_zero_j+1;
			}
			if(one_flag){
				temp_one_j=temp_one_j+1;
			}
			temp_one_i=0;
			temp_zero_i=0;
		}
		int zero_matrix_row_count=temp_matrix_zero.length;
		int zero_matrix_column_count=0;
		if(zero_matrix_row_count>0){
		zero_matrix_column_count=temp_matrix_zero[0].length;}
		else
		{
			zero_matrix_column_count=0;
		}
		int one_matrix_row_count=temp_matrix_one.length;
		int one_matrix_column_count=0;
		if(one_matrix_row_count>0){
		one_matrix_column_count=temp_matrix_one[0].length;}
		else
		{
			one_matrix_column_count=0;
		}
		
		
		//System.out.println("calculating entropy for 0 "+" "+zero_matrix_row_count+" "+zero_matrix_column_count);
		if(true){
		node tree_zero=tree.add();
		int head_node_1=entropy_varience(temp_matrix_zero,zero_matrix_column_count,zero_matrix_row_count+1,tree_zero);
		tree_zero.prev=tree;
		tree.nodes[0]=tree_zero;
		
		if(head_node_1>=0){
			tree_zero.name=zero_top_header[head_node_1];
			tree_zero.parent_value="0";
			tree_zero.leaf_or_not=head_node;
			tree_zero.parent=tree.name;
			//System.out.println("Node now "+zero_top_header[head_node_1]);
			recursive_matrix_variance(tree_zero,temp_matrix_zero,head_node_1,zero_matrix_row_count,zero_matrix_column_count,zero_top_header);
		}
		else
		{  
			String temp_name=null;
			if (head_node_1==-1){
				temp_name="1";
			}
			if(head_node_1==-2){
				temp_name="0";
			}
			tree_zero.name=temp_name;
			tree_zero.parent_value="0";
			tree_zero.leaf_or_not=-1;
			tree_zero.parent=tree.name;
			//System.out.println("leaf value "+temp_name);
		}}
        //System.out.println("calculating entropy for 1 "+" "+one_matrix_row_count+1+" "+one_matrix_column_count);
		if(true){
		node tree_one=tree.add();
		int head_node_2=entropy_varience(temp_matrix_one,one_matrix_column_count,one_matrix_row_count+1,tree_one);
		
		tree_one.prev=tree;
		tree.nodes[1]=tree_one;
		if(head_node_2>=0){
			tree_one.name=zero_top_header[head_node_2];
			tree_one.parent_value="1";
			tree_one.leaf_or_not=head_node;
			tree_one.parent=tree.name;
			recursive_matrix_variance(tree_one,temp_matrix_one,head_node_2,one_matrix_row_count+1,one_matrix_column_count,one_top_header);	
		}
		else{
			String temp_name=null;
			if (head_node_2==-1){
				temp_name="1";
			}
			if(head_node_2==-2){
				temp_name="0";
			}
			tree_one.name=temp_name;
			tree_one.parent_value="1";
			tree_one.leaf_or_not=-1;
			tree_one.parent=tree.name;
			//System.out.println("leaf value "+temp_name);
		}}
		//recursive_matrix(temp_matrix_zero,int head_node,int total_rows,int total_col,String[] top_header)
	}
	public void recursive_matrix(node tree,int[][] matrix,int head_node,int total_rows,int total_col,String[] top_header){
		
		int class_column=total_col-1;
		int zero_count=0;
		int one_count=0;
		for(int i=0;i<total_rows-1;i++){
			
			if(matrix[i][head_node]==0)
			{
				zero_count=zero_count+1;
			}
			if(matrix[i][head_node]==1)
			{
				one_count=one_count+1;
			}
			
		}
		//System.out.println(zero_count+" "+total_col);
		//System.out.println(one_count+" "+total_col);
		int temp_matrix_zero[][]=new int[zero_count][total_col-1];
		int temp_matrix_one[][]=new int[one_count][total_col-1];
		int temp_zero_i=0;
		int temp_zero_j=0;
		int temp_one_i=0;
		int temp_one_j=0;
		boolean zero_flag=false;
		boolean one_flag=true;
		String zero_top_header[]=new String[total_col-1];
		String one_top_header[]=new String[total_col-1];
		for(int j=0;j<total_col;j++){
			
			if(j==head_node){
				continue;
			}
			for(int i=0;i<total_rows-1;i++)
			{
				if(matrix[i][head_node]==0){
					temp_matrix_zero[temp_zero_i][temp_zero_j]=matrix[i][j];
					temp_zero_i=temp_zero_i+1;
					zero_flag=true;
					zero_top_header[temp_zero_j]=top_header[j];
				}
                if(matrix[i][head_node]==1){
                	temp_matrix_one[temp_one_i][temp_one_j]=matrix[i][j];
					temp_one_i=temp_one_i+1;
					one_flag=true;
					one_top_header[temp_one_j]=top_header[j];
				}
				
			}
			if(zero_flag){
				temp_zero_j=temp_zero_j+1;
			}
			if(one_flag){
				temp_one_j=temp_one_j+1;
			}
			temp_one_i=0;
			temp_zero_i=0;
		}
		int zero_matrix_row_count=temp_matrix_zero.length;
		int zero_matrix_column_count=0;
		if(zero_matrix_row_count>0){
		zero_matrix_column_count=temp_matrix_zero[0].length;}
		else
		{
			zero_matrix_column_count=0;
		}
		int one_matrix_row_count=temp_matrix_one.length;
		int one_matrix_column_count=0;
		if(one_matrix_row_count>0){
		one_matrix_column_count=temp_matrix_one[0].length;}
		else
		{
			one_matrix_column_count=0;
		}
		
		
		//System.out.println("calculating entropy for 0 "+" "+zero_matrix_row_count+" "+zero_matrix_column_count);
		if(true){
		node tree_zero=tree.add();
		int head_node_1=entropy(temp_matrix_zero,zero_matrix_column_count,zero_matrix_row_count+1,tree_zero);
		tree_zero.prev=tree;
		tree.nodes[0]=tree_zero;
		
		if(head_node_1>=0){
			tree_zero.name=zero_top_header[head_node_1];
			tree_zero.parent_value="0";
			tree_zero.leaf_or_not=head_node;
			tree_zero.parent=tree.name;
			//System.out.println("Node now "+zero_top_header[head_node_1]);
			recursive_matrix(tree_zero,temp_matrix_zero,head_node_1,zero_matrix_row_count,zero_matrix_column_count,zero_top_header);
		}
		else
		{  
			String temp_name=null;
			if (head_node_1==-1){
				temp_name="1";
			}
			if(head_node_1==-2){
				temp_name="0";
			}
			tree_zero.name=temp_name;
			tree_zero.parent_value="0";
			tree_zero.leaf_or_not=-1;
			tree_zero.parent=tree.name;
			//System.out.println("leaf value "+temp_name);
		}}
        //System.out.println("calculating entropy for 1 "+" "+one_matrix_row_count+1+" "+one_matrix_column_count);
		if(true){
		node tree_one=tree.add();
		int head_node_2=entropy(temp_matrix_one,one_matrix_column_count,one_matrix_row_count+1,tree_one);
		
		tree_one.prev=tree;
		tree.nodes[1]=tree_one;
		if(head_node_2>=0){
			tree_one.name=zero_top_header[head_node_2];
			tree_one.parent_value="1";
			tree_one.leaf_or_not=head_node;
			tree_one.parent=tree.name;
			recursive_matrix(tree_one,temp_matrix_one,head_node_2,one_matrix_row_count+1,one_matrix_column_count,one_top_header);	
		}
		else{
			String temp_name=null;
			if (head_node_2==-1){
				temp_name="1";
			}
			if(head_node_2==-2){
				temp_name="0";
			}
			tree_one.name=temp_name;
			tree_one.parent_value="1";
			tree_one.leaf_or_not=-1;
			tree_one.parent=tree.name;
			//System.out.println("leaf value "+temp_name);
		}}
		//recursive_matrix(temp_matrix_zero,int head_node,int total_rows,int total_col,String[] top_header)
	}
	public int entropy(int[][] matrix,int total_column,int total_row,node tree)
	{
		int class_column=total_column-1;
		int e_positive=0;
		int e_negative=0;
		total_row=total_row-1;
		for(int i=0;i<total_row;i++)
		{
			if (matrix[i][class_column]==1){
				e_positive=e_positive+1;
			}
			else
			{
				e_negative=e_negative+1;
			}
		}
		//System.out.println(e_positive+" "+e_negative+" "+total_row);
		if(e_positive==total_row){
			return -1;
		}
		if(e_negative==total_row){
			return -2;
		}
		if(total_column==2){
			if(e_positive>e_negative){
				return -1;
			}
			else
			{
				return -2;
			}
		}
		tree.positive_count=e_positive;
		tree.negative_count=e_negative;
		double positive_fraction=(double)((double)e_positive/(double)total_row);
		double negative_fraction=(double)((double)e_negative/(double)total_row);
		if(positive_fraction==0){
			positive_fraction=1;
		}
		if(negative_fraction==0){
			negative_fraction=1;
		}
		double total_entropy=(-(positive_fraction)*((Math.log10(positive_fraction)/Math.log10(2.0d)))-(negative_fraction)*((Math.log10(negative_fraction)/Math.log10(2.0d))));
		//System.out.println((Math.log10(positive_fraction)/Math.log10(2.0d))+" "+(Math.log10(negative_fraction)/Math.log10(2.0d))+" "+total_row);
		double max=0;
		int max_column=1;
		for(int j=0;j<(total_column-1);j++){
			
			int zero_attri_positive=0;
			int zero_attri_total=0;
			int one_attri_positive=0;
			int one_attri_total=0;
			for(int i=0;i<total_row;i++){
				
				if(matrix[i][j]==0)
				{
					//System.out.println("matrix-0"+matrix[i][j]);
					zero_attri_total=zero_attri_total+1;
					if(matrix[i][class_column]==1)
					{
						zero_attri_positive=zero_attri_positive+1;
					}
				}
				if(matrix[i][j]==1)
				{
					//System.out.println("matrix-0"+matrix[i][j]);
					one_attri_total=one_attri_total+1;
					if(matrix[i][class_column]==1)
					{
						one_attri_positive=one_attri_positive+1;
					}
				}
				
			}
			double zero_attribute_total_fraction=0;
			double one_attribute_total_fraction=0;
			double zero_attribute_positive_fraction=0;
			double zero_attribute_negative_fraction=0;
			double one_attribute_positive_fraction=0;
			double one_attribute_negative_fraction=0;
	        //System.out.println(zero_attri_total+" "+total_row);
	        //System.out.println(one_attri_total+" "+total_row);
			//System.out.println(zero_attri_total+" "+one_attri_total);
			//System.out.println(zero_attri_positive+" "+one_attri_positive);
			zero_attribute_total_fraction=(double)((double)zero_attri_total/(double)total_row);
			one_attribute_total_fraction=(double)((double)one_attri_total/(double)total_row);
			if(zero_attri_total!=0){
			zero_attribute_positive_fraction=(double)zero_attri_positive/(double)zero_attri_total;
			zero_attribute_negative_fraction=((double)zero_attri_total-(double)zero_attri_positive)/(double)zero_attri_total;}
			if(one_attri_total!=0){
			one_attribute_positive_fraction=(double)one_attri_positive/(double)one_attri_total;
			one_attribute_negative_fraction=((double)one_attri_total-(double)one_attri_positive)/(double)one_attri_total;}
			if(zero_attribute_total_fraction==0){
				zero_attribute_total_fraction=1;
			}
			if(one_attribute_total_fraction==0){
				one_attribute_total_fraction=1;
			}	
			if(zero_attribute_positive_fraction==0){
				zero_attribute_positive_fraction=1;
			}
			if(zero_attribute_negative_fraction==0){
				zero_attribute_negative_fraction=1;
			}
			if(one_attribute_positive_fraction==0){
				one_attribute_positive_fraction=1;
			}
			if(one_attribute_negative_fraction==0){
				one_attribute_negative_fraction=1;
			}	
			//System.out.println(zero_attribute_positive_fraction+" "+zero_attribute_negative_fraction);
			double gain_1=-(zero_attribute_positive_fraction*(Math.log10(zero_attribute_positive_fraction)/Math.log10(2.0d)))-(zero_attribute_negative_fraction*(Math.log10(zero_attribute_negative_fraction)/Math.log10(2.0d)));
			double gain_2=-(one_attribute_positive_fraction*(Math.log10(one_attribute_positive_fraction)/Math.log10(2.0d)))-(one_attribute_negative_fraction*(Math.log10(one_attribute_negative_fraction)/Math.log10(2.0d)));
			//System.out.println(gain_1+" "+zero_attribute_total_fraction);
			gain_1=zero_attribute_total_fraction*gain_1;
			
			//System.out.println(gain_2);
			gain_2=one_attribute_total_fraction*gain_2;
			double sum_gain=gain_1+gain_2;
			//System.out.println(sum_gain);
			double gain_total=total_entropy-sum_gain;
			//System.out.println(total_entropy+" "+sum_gain+" "+gain_total);
			if(gain_total>=max){
				max=gain_total;
				max_column=j;
			}
		}
			
		//System.out.println(max_column);
		return max_column;
	}
	public int entropy_varience(int[][] matrix,int total_column,int total_row,node tree)
	{
		int class_column=total_column-1;
		int e_positive=0;
		int e_negative=0;
		total_row=total_row-1;
		for(int i=0;i<total_row;i++)
		{
			if (matrix[i][class_column]==1){
				e_positive=e_positive+1;
			}
			else
			{
				e_negative=e_negative+1;
			}
		}
		//System.out.println(e_positive+" "+e_negative+" "+total_row);
		if(e_positive==total_row){
			return -1;
		}
		if(e_negative==total_row){
			return -2;
		}
		if(total_column==2){
			if(e_positive>e_negative){
				return -1;
			}
			else
			{
				return -2;
			}
		}
		tree.positive_count=e_positive;
		tree.negative_count=e_negative;
		double positive_fraction=(double)((double)e_positive/(double)total_row);
		double negative_fraction=(double)((double)e_negative/(double)total_row);
		if(positive_fraction==0){
			positive_fraction=1;
		}
		if(negative_fraction==0){
			negative_fraction=1;
		}
		double total_entropy=((positive_fraction)*(negative_fraction));
		//System.out.println((Math.log10(positive_fraction)/Math.log10(2.0d))+" "+(Math.log10(negative_fraction)/Math.log10(2.0d))+" "+total_row);
		double max=0;
		int max_column=1;
		for(int j=0;j<(total_column-1);j++){
			
			int zero_attri_positive=0;
			int zero_attri_total=0;
			int one_attri_positive=0;
			int one_attri_total=0;
			for(int i=0;i<total_row;i++){
				
				if(matrix[i][j]==0)
				{
					//System.out.println("matrix-0"+matrix[i][j]);
					zero_attri_total=zero_attri_total+1;
					if(matrix[i][class_column]==1)
					{
						zero_attri_positive=zero_attri_positive+1;
					}
				}
				if(matrix[i][j]==1)
				{
					//System.out.println("matrix-0"+matrix[i][j]);
					one_attri_total=one_attri_total+1;
					if(matrix[i][class_column]==1)
					{
						one_attri_positive=one_attri_positive+1;
					}
				}
				
			}
			double zero_attribute_total_fraction=0;
			double one_attribute_total_fraction=0;
			double zero_attribute_positive_fraction=0;
			double zero_attribute_negative_fraction=0;
			double one_attribute_positive_fraction=0;
			double one_attribute_negative_fraction=0;
	        //System.out.println(zero_attri_total+" "+total_row);
	        //System.out.println(one_attri_total+" "+total_row);
			//System.out.println(zero_attri_total+" "+one_attri_total);
			//System.out.println(zero_attri_positive+" "+one_attri_positive);
			zero_attribute_total_fraction=(double)((double)zero_attri_total/(double)total_row);
			one_attribute_total_fraction=(double)((double)one_attri_total/(double)total_row);
			if(zero_attri_total!=0){
			zero_attribute_positive_fraction=(double)zero_attri_positive/(double)zero_attri_total;
			zero_attribute_negative_fraction=((double)zero_attri_total-(double)zero_attri_positive)/(double)zero_attri_total;}
			if(one_attri_total!=0){
			one_attribute_positive_fraction=(double)one_attri_positive/(double)one_attri_total;
			one_attribute_negative_fraction=((double)one_attri_total-(double)one_attri_positive)/(double)one_attri_total;}
			if(zero_attribute_total_fraction==0){
				zero_attribute_total_fraction=1;
			}
			if(one_attribute_total_fraction==0){
				one_attribute_total_fraction=1;
			}	
			if(zero_attribute_positive_fraction==0){
				zero_attribute_positive_fraction=1;
			}
			if(zero_attribute_negative_fraction==0){
				zero_attribute_negative_fraction=1;
			}
			if(one_attribute_positive_fraction==0){
				one_attribute_positive_fraction=1;
			}
			if(one_attribute_negative_fraction==0){
				one_attribute_negative_fraction=1;
			}	
			//System.out.println(zero_attribute_positive_fraction+" "+zero_attribute_negative_fraction);
			double gain_1=(zero_attribute_positive_fraction*zero_attribute_negative_fraction);
			double gain_2=(one_attribute_positive_fraction*one_attribute_negative_fraction);
			//System.out.println(gain_1+" "+zero_attribute_total_fraction);
			gain_1=zero_attribute_total_fraction*gain_1;
			
			//System.out.println(gain_2);
			gain_2=one_attribute_total_fraction*gain_2;
			double sum_gain=gain_1+gain_2;
			//System.out.println(sum_gain);
			double gain_total=total_entropy-sum_gain;
			//System.out.println(total_entropy+" "+sum_gain+" "+gain_total);
			if(gain_total>=max){
				max=gain_total;
				max_column=j;
			}
		}
			
		//System.out.println(max_column);
		return max_column;
	}
	public float calculate_accuracy_main(int[][] matrix,int rows,int columns,String[] header_rows,node tree){
		
		int positive_count=0;
		for(int i=0;i<rows-1;i++){
			int return_value=tree.accuracy(matrix,i,columns-1,header_rows);
			if(return_value==1)
			{   
				positive_count=positive_count+1;
			}
		}
		return (float)(positive_count)/(float)(rows-1);
	}
	public node pruning(int[][] validation,int row_count,int column_count,String[] header_rows,node tree,int L,int k,InputRead ir){
		
		        node tree_max=new node();
			    tree.treecopy(tree_max);
			    int L_count=0;
			    float max_accuracy=ir.calculate_accuracy_main(validation,row_count,column_count,header_rows,tree);
			    
			    while(true){
			            
			    	    List<node> stack_sample=new ArrayList<node>();
			            int count=1;
			            node tree_sample=new node();
			            tree.treecopy(tree_sample);
			            Random r = new Random();
			            
			               int M=(int)r.nextInt(k-1)+1;
			              
			            int M_count=0;
			            while(true){
			            	stack_sample=new ArrayList<node>();
			                tree_sample.formstach(stack_sample);
			                
			                if(stack_sample.size()<=2)
			                    break;
			                
			               
			                  int N=(int)r.nextInt(stack_sample.size()-2)+1;			                
			               
			                node node_to_drop=stack_sample.get(N);
			
               int node_to_p=node_to_drop.positive_count;
			                int node_to_n=node_to_drop.negative_count;
			                node previous_node=node_to_drop.prev;
			                int i;
			                 if(previous_node==null)
			                	break;
			                if(previous_node.leaf_or_not==-1)
			                	break; 
			                if(previous_node.nodes[0].name.equals("1") || previous_node.nodes[0].name.equals("0"))
			                {
			                	break;
			                }
			                if(previous_node.nodes[1].name.equals("1") || previous_node.nodes[1].name.equals("0"))
			                {
			                	break;
			                }
			                for(i=0;i<=1;i++){
			                	//System.out.println(previous_node.nodes[i].name+" "+node_to_drop.name);
			                	if(previous_node.nodes[i].name.equals(node_to_drop.name))
			                		break;
			                }
			                node newnode=previous_node.add();
			                previous_node.nodes[i]=newnode;
			                if(node_to_p > node_to_n)
			                   newnode.name="1";
			                else
			                   newnode.name="0";
			                newnode.parent_value=node_to_drop.parent_value;
			                newnode.leaf_or_not=-1;
			                newnode.positive_count=node_to_p;
			                newnode.negative_count=node_to_n;
			                if(M_count==M)
			                    break;
			                M_count=M_count+1;}
			            float accuracy_now=ir.calculate_accuracy_main(validation, row_count, column_count, header_rows,tree_sample);
			            
			            if (accuracy_now>max_accuracy){
			                max_accuracy=accuracy_now;
			                tree_max=new node();
			                tree_sample.treecopy(tree_max);}
			            if(L_count==L)
			                break;
			            L_count=L_count+1;
			            stack_sample=new ArrayList<node>();
			            tree_sample.formstach(stack_sample);
			            
			    }
			            
			    return tree_max;
	}
	public static void main(String args[])throws Exception{
		
	
		InputRead ir=new InputRead();
		node tree=new node();
		System.out.println("Enter the path of Training set data, test set data, validation set data, L value and K value in order:");
		Scanner sc=new Scanner(System.in);
		
		String training_set=sc.nextLine();
		String test_set=sc.nextLine();
		String validation_set=sc.nextLine();
		int l_value=sc.nextInt();
		int k_value=sc.nextInt();
		
		matrix_form training=new matrix_form(training_set);
		matrix_form validation=new matrix_form(validation_set);
		matrix_form test=new matrix_form(test_set);
		int head_node=ir.entropy(training.matrix,training.col_count,training.row_count,tree);
		tree.name=training.top_header[head_node];
		tree.parent_value=null;
		tree.leaf_or_not=head_node;
		header_rows=training.top_header;
		ir.recursive_matrix(tree,training.matrix,head_node,training.row_count,training.col_count,training.top_header);
		System.out.println("Decision tree for training set using information gain heuristic:");
		tree.traverse();
		float total_accuracy=ir.calculate_accuracy_main(test.matrix,test.row_count,test.col_count,header_rows,tree);
		System.out.println("accuracy before pruning using information gain:"+" "+total_accuracy);
		node tree_after=ir.pruning(test.matrix,test.row_count,test.col_count,header_rows,tree,l_value,k_value,ir);
		System.out.println("Decision tree after pruning using information heuristic:");
		tree_after.traverse();
		float total_accuracy_after=ir.calculate_accuracy_main(validation.matrix,validation.row_count,validation.col_count,header_rows,tree_after);
		System.out.println("accuracy after pruning using information gain "+total_accuracy_after);
		
		System.out.println();
		node tree_varience=new node();
		int head_node_varience=ir.entropy_varience(training.matrix,training.col_count,training.row_count,tree_varience);
		tree_varience.name=training.top_header[head_node_varience];
		tree_varience.parent_value=null;
		tree_varience.leaf_or_not=head_node_varience;
		header_rows=training.top_header;
		ir.recursive_matrix_variance(tree_varience,training.matrix,head_node,training.row_count,training.col_count,training.top_header);
		System.out.println("Decision tree for training set using impurity variance heuristic:");
		tree_varience.traverse();
		float total_accuracy_varience=ir.calculate_accuracy_main(test.matrix,test.row_count,test.col_count,header_rows,tree_varience);
		System.out.println("accuracy before pruning using impurity variance"+" "+total_accuracy_varience);
		node tree_after_varience=ir.pruning(test.matrix,test.row_count,test.col_count,header_rows,tree_varience,l_value,k_value,ir);
		System.out.println("Decision tree after pruning using impurity variance:");
		tree_after_varience.traverse();
		float total_accuracy_after_varience=ir.calculate_accuracy_main(validation.matrix,validation.row_count,validation.col_count,header_rows,tree_after_varience);
		System.out.println("accuracy after pruning impurity variance "+total_accuracy_after_varience);
	}
	
}