//リンクしているものを表示するプログラム
//指定した言葉のカテゴリーを表示するプログラム

import java.util.*;
import java.io.*;

public class Search{
    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);


	int linkSize=52973671;  //links.txtの総行数 
	int pageSize=1483277;  //pages.txtの総行数
	int catsSize=229894;   //cats.txtの総行数
	int catlinksSize=4552836;  //cat_links.txtの行数
	
	int[] linkId=new int[linkSize];  //リンク元のページのid
	int[] linkedId=new int[linkSize];  //リンク先のページのid
	
	int[] pageId=new int[pageSize];  //ページのid
	String[] word=new String[pageSize];  //ページの見出し語

	int[] catId=new int[catsSize];//カテゴリーのid
	String[] catword=new String[catsSize];//カテゴリーの種類

	int[] catlinkid=new int[catlinksSize];//ページのid
	int[] catlinkedid=new int[catlinksSize];//リンクしているカテゴリーのid

	System.out.println("Wikipediaのカテゴリーを表示したい言葉");
	String searchw = sc.next();//探したい言葉
	int sid = -1;//探したい言葉のid


	ArrayList<Integer> idlist = new ArrayList<Integer>();//リンクしている言葉のid
	ArrayList<String> findans = new ArrayList<String>();//リンクしている言葉のリスト
	ArrayList<Integer> scid = new ArrayList<Integer>();//入力した言葉のカテゴリーid
	ArrayList<String> scword = new ArrayList<String>();//入力した言葉のカテゴリー	

	try{  //pages.txtを読み込む、探したい言葉のidを格納する
	    File file=new File("pages.txt");
	    BufferedReader br=new BufferedReader(new FileReader(file));
	    String str=br.readLine();
	    for(int i = 0;str!=null;i++){
		String[] pagesf=str.split("\t");  //タブで区切る
		pageId[i]=Integer.parseInt(pagesf[0]);  //タブの前
		word[i]=pagesf[1];  //タブの後
		if(searchw.equals(word[i])){sid=pageId[i];}
		str=br.readLine();
	    }   
	    br.close();
	}catch(FileNotFoundException e){
	    System.out.println(e);
	}catch(IOException e){
	    System.out.println(e);
	}
	if(sid==-1){
	    System.out.println("探している言葉はありません");
	    return;
	}
	

	try{  //links.txtを読み込む、リンクしている言葉のidを格納する
	    File file=new File("links.txt");
	    BufferedReader br=new BufferedReader(new FileReader(file));
	    String str=br.readLine();
	    for(int i = 0;str!=null;i++){
		String[] linksf=str.split("\t");  //タブで区切る
		linkId[i]=Integer.parseInt(linksf[0]);  //タブの前
		linkedId[i]=Integer.parseInt(linksf[1]);  //タブの後
		if(linkId[i]==sid){ idlist.add(linkedId[i]); }   
		str=br.readLine();
	    }
	    br.close();
	}catch(FileNotFoundException e){
	    System.out.println(e);
	}catch(IOException e){
	    System.out.println(e);
	}

	try{  //cat_links.txtを読み込む
	    File file=new File("cat_links.txt");
	    BufferedReader br=new BufferedReader(new FileReader(file));
	    String str=br.readLine();
	    for(int i = 0;str!=null;i++){
		String[] catsf=str.split("\t");  //タブで区切る
		catlinkid[i]=Integer.parseInt(catsf[0]);  //タブの前
		catlinkedid[i]=Integer.parseInt(catsf[1]);  //タブの後
		if(catlinkid[i]==sid){scid.add(catlinkedid[i]);}
		str=br.readLine();
	    }
	    br.close();
	}catch(FileNotFoundException e){
	    System.out.println(e);
	}catch(IOException e){
	    System.out.println(e);
	}
	
	try{  //cats.txtを読み込む
	    File file=new File("cats.txt");
	    BufferedReader br=new BufferedReader(new FileReader(file));
	    String str=br.readLine();
	    for(int i = 0;str!=null;i++){
		String[] catsf=str.split("\t");  //タブで区切る
		catId[i]=Integer.parseInt(catsf[0]);  //タブの前
		catword[i]=catsf[1];  //タブの後
		for(int id:scid){
		    if(id==catId[i]){
			scword.add(catword[i]);
		    }
		}
		str=br.readLine();
	    }
	    br.close();
	}catch(FileNotFoundException e){
	    System.out.println(e);
	}catch(IOException e){
	    System.out.println(e);
	}
	//System.out.println("リンクされている言葉");
	//print(idlist,findans,word);
	System.out.println(searchw+"のカテゴリー");
	for(int i = 0;i < scword.size();i++){
	    System.out.println(scword.get(i));
	}
    }
    
    //リンクされている言葉を表示させるメソッド
    public static void print(ArrayList<Integer> idlist,ArrayList<String> findans,String[] word){
	for(int list:idlist){
	    findans.add(word[list]);
	    System.out.println("id = "+ list+ " : header = "+word[list]);
	}
	
	if(findans.size()<=0){
	    System.out.println("見つかりませんでした");
	}
    }
}
