insert into guest values('test','1234','テスター','京都部京都市どこかに','010-1234-5678',1000,default,default,default);
insert into guest values('leesan','1234','李','東京都のどこかに','010-5854-5678',1000,default,default,default);
insert into guest values('liver','1234','ライバ','韓国ソウル','010-3248-5211',1000,default,default,default);
insert into guest values('operation','1234','オファ','韓国デェヂョン','010-1555-9557',1000,default,default,default);

insert into guest values('admin','1234','管理者','JSLSALONG','010-0000-0000',default,default,1,default);

insert into designer values(10,1,'LEE',1);
insert into designer values(11,1,'HA',1);
insert into designer values(12,1,'SIM',1);
insert into designer values(13,1,'SHIN',1);
insert into designer values(20,2,'HA',2);
insert into designer values(21,3,'YOON',2);
insert into designer values(22,4,'LEE',2);
insert into designer values(23,1,'KIM',2);
insert into designer values(30,2,'NANASHI',3);
insert into designer values(31,3,'DOKOUDA',3);
insert into designer values(32,4,'NANDA',3);
insert into designer values(33,1,'KOREWA',3);
insert into designer values(40,2,'NANAYA',4);
insert into designer values(41,3,'SAKATA',4);
insert into designer values(42,4,'NINNIKU',4);
insert into designer values(43,1,'NINJIN',4);

insert into reservation values(numb2_seq.nextval,'test',1,1,to_date('2017-08-01','yyyy-MM-dd'),1800,10,default);
insert into reservation values(numb2_seq.nextval,'leesan',1,1,to_date('2017-08-20','yyyy-MM-dd'),1600,41,default);
insert into reservation values(numb2_seq.nextval,'test',1,1,to_date('2017-08-19','yyyy-MM-dd'),1000,40,default);
insert into reservation values(numb2_seq.nextval,'operation',1,1,to_date('2017-08-19','yyyy-MM-dd'),2000,42,default);
insert into reservation values(numb2_seq.nextval,'test',1,1,to_date('2017-08-17','yyyy-MM-dd'),1100,10,default);
insert into reservation values(numb2_seq.nextval,'liver',1,1,to_date('2017-08-17','yyyy-MM-dd'),1300,23,default);
insert into reservation values(numb2_seq.nextval,'liver',1,1,to_date('2017-08-16','yyyy-MM-dd'),1400,10,default);
insert into reservation values(numb2_seq.nextval,'test',1,1,to_date('2017-08-16','yyyy-MM-dd'),1500,11,default);
insert into reservation values(numb2_seq.nextval,'operation',1,1,to_date('2017-08-15','yyyy-MM-dd'),1000,33,default);
insert into reservation values(numb2_seq.nextval,'leesan',1,1,to_date('2017-08-15','yyyy-MM-dd'),1200,22,default);

insert into board values(numb1_seq.nextval,'test','スキ','ないよう1','user_photo_06.jpg',default);
insert into board values(numb1_seq.nextval,'test','いいね','ないよう1','user_photo_03.jpg',default);
insert into board values(numb1_seq.nextval,'leesan','良かったです','ないよう3','user_photo_01.jpg',default);
insert into board values(numb1_seq.nextval,'test','良かったです','ないよう3','user_photo_02.jpg',default);
insert into board values(numb1_seq.nextval,'leesan','大丈夫','ないよう5','user_photo_08.jpg',default);
insert into board values(numb1_seq.nextval,'operation','良かったです','ないよう6','user_photo_07.jpg',default);
insert into board values(numb1_seq.nextval,'operation','また来ます','ないよう7','user_photo_05.jpg',default);
insert into board values(numb1_seq.nextval,'test','また来ます','ないよう8','user_photo_02.jpg',default);
insert into board values(numb1_seq.nextval,'liver','良かったです','ないよう9','user_photo_04.jpg',default);
insert into board values(numb1_seq.nextval,'test','また来ます','ないよう10','user_photo_06.jpg',default);
insert into board values(numb1_seq.nextval,'leesan','また来ます','ないよう11','user_photo_08.jpg',default);
insert into board values(numb1_seq.nextval,'test','良かったです','ないよう12','user_photo_05.jpg',default);
insert into board values(numb1_seq.nextval,'leesan','いいね','ないよう13','user_photo_03.jpg',default);
insert into board values(numb1_seq.nextval,'test','제목14','ないよう14','user_photo_01.jpg',default);
insert into board values(numb1_seq.nextval,'test','いいね','ないよう15','user_photo_07.jpg',default);
insert into board values(numb1_seq.nextval,'liver','良かったです','ないよう16','user_photo_08.jpg',default);
insert into board values(numb1_seq.nextval,'test','大丈夫','ないよう17','user_photo_01.jpg',default);
insert into board values(numb1_seq.nextval,'liver','大丈夫','ないよう18','user_photo_02.jpg',default);
insert into board values(numb1_seq.nextval,'test','また来ます','ないよう19','user_photo_01.jpg',default);
insert into board values(numb1_seq.nextval,'admin','また来ます','ないよう20','user_photo_06.jpg',default);

insert into board2 values(numb2_seq.nextval,'test','質問です','いくらですか','1000円です。',default);
insert into board2 values(numb2_seq.nextval,'liver','これなんですか','いいですね','なんですか',default);
insert into board2 values(numb2_seq.nextval,'liver','これなんですか','いいですね',default,default);
insert into board2 values(numb2_seq.nextval,'test','質問です','いくらですか','32000円です。',default);
insert into board2 values(numb2_seq.nextval,'test','質問です','何人ですか','5人です',default);
insert into board2 values(numb2_seq.nextval,'test','質問です','はい','いいえ',default);
insert into board2 values(numb2_seq.nextval,'operation','これなんですか','はい','いいえ',default);
insert into board2 values(numb2_seq.nextval,'liver','いくらですか','いくらですか',default,default);
insert into board2 values(numb2_seq.nextval,'test','質問です','いくらですか','32000円です。',default);
insert into board2 values(numb2_seq.nextval,'test','質問です','いくらですか','32000円です。',default);
insert into board2 values(numb2_seq.nextval,'leesan','いくらですか','なんですか','いいえ',default);
insert into board2 values(numb2_seq.nextval,'leesan','いくらですか','なんですか','いいえ',default);
insert into board2 values(numb2_seq.nextval,'test','これなんですか','なんですか','いいえ',default);
insert into board2 values(numb2_seq.nextval,'test','これなんですか','なんですか',default,default);
insert into board2 values(numb2_seq.nextval,'test','質問です','質問はここ','なんですか',default);
insert into board2 values(numb2_seq.nextval,'operation','はあ','え','なんでしょう',default);
insert into board2 values(numb2_seq.nextval,'leesan','質問です','はい','JSL',default);
insert into board2 values(numb2_seq.nextval,'test','質問です','あ','JSL',default);
insert into board2 values(numb2_seq.nextval,'test','質問です','い','JSL',default);
insert into board2 values(numb2_seq.nextval,'test','いくらですか','だめです',default,default);


insert into pointlog values('test',1000,'会員登録特典',to_date('2017-06-20','YYYY-MM-DD'));
insert into pointlog values('leesan',1000,'会員登録特典',to_date('2017-06-02','YYYY-MM-DD'));
insert into pointlog values('liver',1000,'会員登録特典',to_date('2017-07-22','YYYY-MM-DD'));
insert into pointlog values('operation',1000,'会員登録特典',to_date('2017-07-22','YYYY-MM-DD'));

insert into businesstime values(numb2_seq.nextval,1,'08:30','20:30',default);
insert into businesstime values(numb2_seq.nextval,2,'07:40','21:40',default);
insert into businesstime values(numb2_seq.nextval,3,'07:00','04:00',default);

insert into style values(numb2_seq.nextval,'color','ことりベージュ','color01.jpg','休みの前に明るいカラーがしたい方',default);
insert into style values(numb2_seq.nextval,'color','ツートーンカラー','color02.jpg','ナチュラルなカラーはほしい方',default);
insert into style values(numb2_seq.nextval,'color','チェリーピンクツートーンカラー','color03.jpg','ナチュラルなツートーンはほしい方',default);
insert into style values(numb2_seq.nextval,'color','オリーブマットブラウン','color04.jpg','穏やかで女性らしいカラーがほしい方',default);
insert into style values(numb2_seq.nextval,'color','オーロラピンク','color05.jpg','カラーだけでもイメージチェンジ',default);
insert into style values(numb2_seq.nextval,'long','レイヤーストレート','long01.jpg','おしゃれなスタイルを望んでる方',default);
insert into style values(numb2_seq.nextval,'long','アーティフィシャルブラッサム','long02.jpg','ブリーチ2回の後4回のオレンジーとピンクをミックスマッチしてカラーリング',default);
insert into style values(numb2_seq.nextval,'long','レイヤーパーマロング','long03.jpg','おしゃれなカールを望む方',default);
insert into style values(numb2_seq.nextval,'long','ナチュラルヒッピーパーマ','long04.jpg','水に流されてるようなナチュラルヒッピーパーマ',default);
insert into style values(numb2_seq.nextval,'long','cカールパーマ','long05.jpg','髪の毛が多い方やスタイリングが難しい方',default);
insert into style values(numb2_seq.nextval,'medium','グラムパーマ','medium01.jpg','パーマが残っていたらカットだけでも体験できる感じ',default);
insert into style values(numb2_seq.nextval,'medium','ボディパーマ','medium02.jpg','大きめのカールでナチュラルとふんわりしてる感じのウェブがほしい方',default);
insert into style values(numb2_seq.nextval,'medium','cカールパーマ','medium03.jpg','スタイリングしやすくて明るいスタイル',default);
insert into style values(numb2_seq.nextval,'medium','ショートロングパーマ','medium04.jpg','ボリューム感がほしい方',default);
insert into style values(numb2_seq.nextval,'men','リジェントパーマ','men01.jpg','落ち着いたスタイルがほしい方',default);
insert into style values(numb2_seq.nextval,'men','ツーブラック','men02.jpg','ダンディなスタイルがほしい方',default);
insert into style values(numb2_seq.nextval,'men','ソフトパーマ','men03.jpg','スタイリングしやすいのがほしい方',default);
insert into style values(numb2_seq.nextval,'men','リジェントパーマ','men04.jpg','落ち着いたスタイルがほしい方',default);
insert into style values(numb2_seq.nextval,'short','ショートカット','short01.jpg','おしゃれでかわいいスタイル',default);
insert into style values(numb2_seq.nextval,'short','短髪cカールパーマ','short02.jpg','ナチュラルなcカールがほしい方',default);
insert into style values(numb2_seq.nextval,'short','根元立ち上げパーマ','short03.jpg','顔を小さく見えるようにして、生気があるように見える効果',default);
insert into style values(numb2_seq.nextval,'short','ショートカット','short04.jpg','簡単なドライだけでスタイリングできるスタイル',default);
insert into style values(numb2_seq.nextval,'short','ショートカット','short05.jpg','スタイリングが簡単な髪型がほしい方',null,default);


insert into event values(numb2_seq.nextval, 'デザイナおすすめ割引イベント', '                                   　　　デザイナおすすめヘアースタイル(パーマの中１)をするお客様には20％の割引が！<br><br>                
                                    　　イベント開催店舗：JSL SALON本店、JSL SALON秋葉原店<br><br>
                                 　　   イベント参加方法：当店舗からご参加してください<br><br>', 'image1.jpg', 'style', to_date('2017-08-18','YYYY-MM-DD'), default);
insert into event values(numb2_seq.nextval, 'クーポンイベント', 'ホームページから予約していらっしゃるお客様には <br>各種サービスを提供するクーポンブックを差し上げます。<br><br>                 
                                 　　イベント開催店舗：JSL SALON全支店<br><br>
                                 　　イベント参加方法：ホームページから予約ページをスクリーンショットで撮って<br>スタッフに見せてください。クーポンブックを差し上げます<br><br>  
                * クーポンブックは発行した当日には使えません。ご了承ください。 *<br>', 'image2.jpg', 'booking', to_date('2017-08-18','YYYY-MM-DD'), default);
insert into event values(numb2_seq.nextval, '口コミイベント', 'JSL SALONの利用後、口コミを書いてください！<br>口コミを書いてくださった方にはポイント1000点が！<br><br>              
                                   　 イベント開催店舗：JSL SALON全支店<br><br>
                                 　　イベント参加方法：JSL SALONを利用した後<br>会員登録後口コミにレジューを書いてください。<br><br> 
                * 月末に口コミイベントもございますのでどうかご参加してください。 *<br>   ', 'image3.jpg', 'board', to_date('2017-08-18','YYYY-MM-DD'), default);
