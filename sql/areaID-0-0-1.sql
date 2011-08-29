
	create table "tblAnswer" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"lngAnswerID" integer, 
		
		"lngQuestionnaireID" integer, 
		
		"lngQuestionID" integer, 
		
		"strQuestion" text, 
		
		"strAnswer" text, 
		
		"strAnswerOptionPicked" Varchar (200)
	);

	create table "tblAudioPrompt" (
	
	
	
		
		"lngAudioPromptID" integer, 
		
		"strAudioPromptName" text, 
		
		"intAudioPromptType" integer, 
		
		"strAudioPromptDescription" text, 
		
		"strAudioPromptText" text, 
		
		"strAudioPromptMediaPath" text
	);

	create table "tblQuestion" (
	
	
	
		
		"lngQuestionID" integer, 
		
		"lngQuestionTypeID" integer, 
		
		"strQuestionText" text, 
		
		"strQuestionMediaPath" text
	);

	create table "tblQuestionnaire" (
	
	
	
		
		"lngQuestionnaireID" integer, 
		
		"strQuestionnaireName" text, 
		
		"strQuestionnaireDescription" text, 
		
		"strQuestionnaireCustom1" text, 
		
		"strQuestionnaireCustom2" text, 
		
		"strQuestionnaireCustom3" text
	);

	create table "tblQuestionnaireXQuestion" (
	
	
	
		
		"lngQuestionnaireID" integer, 
		
		"lngQuestionID" integer, 
		
		"strQuestionnaireXQuestionCustom1" text, 
		
		"strQuestionnaireXQuestionCustom2" text, 
		
		"strQuestionnaireXQuestionCustom3" text, 
		
		"lngQuestionnaireXQuestionSequence" integer
	);

	create table "tblQuestionResponseOption" (
	
	
	
		
		"lngQuestionResponseOptionID" integer, 
		
		"strQuestionResponseOptionName" text, 
		
		"lngQuestionResponseOptionName" integer, 
		
		"strQuestionResponseOptioMediaPath" text
	);

	create table "tblQuestionType" (
	
	
	
		
		"lngQuestionTypeID" integer, 
		
		"strQuestionTypeName" text, 
		
		"strQuestionTypeCustom1" text, 
		
		"strQuestionTypeCustom2" text, 
		
		"strQuestionTypeCustom3" text
	);

	create table "tblQuestionXResponseOption" (
	
	
	
		
		"lngQuestionID" integer, 
		
		"lngQuestionResponseOptionID" integer, 
		
		"strQuestionXResponseOptionCustom1" Varchar (50), 
		
		"strQuestionXResponseOptionCustom2" Varchar (50), 
		
		"strQuestionXResponseOptionCustom3" Varchar (50)
	);

	create table "tblSubject" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"strSubjectFirstName" text, 
		
		"strSubjectLastName" text, 
		
		"blnSubjectFemale" boolean, 
		
		"dtmSubjectBirthdate" Date, 
		
		"strSubjectDataVerification" text, 
		
		"strSubjectEmail" text, 
		
		"strSubjectPassword" text, 
		
		"strSubjectCustom1" text, 
		
		"strSubjectCustom2" text, 
		
		"strSubjectCustom3" text
	);

	create table "tblSubjectAudioFile" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"lngSubjectAudioFileID" integer, 
		
		"strSubjectAudioFilePath" text, 
		
		"strSubjectAudioFileCustom1" text, 
		
		"strSubjectAudioFileCustom2" text, 
		
		"strSubjectAudioFileCustom3" text, 
		
		"strSubjectAudioFileMetaInfo" text, 
		
		"strSubjectAudioFileVerification" text, 
		
		"lngAudioIncentiveID" integer
	);

	create table "tblSubjectAudioFileXAnswer" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"lngSubjectAudioFileID" integer, 
		
		"lngAnswerID" integer, 
		
		"strSubjectAudioFileXAnswerCustom1" text, 
		
		"strSubjectAudioFileXAnswerCustom2" text, 
		
		"strSubjectAudioFileXAnswerCustom3" text
	);

	create table "tblSubjectResidence" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"lngSubjectResidenceID" integer, 
		
		"strSubjectResidenceCountry" text, 
		
		"strSubjectResidenceZIP" Varchar (10), 
		
		"strSubjectResidenceStreet" text, 
		
		"strSubjectResidenceCity" text, 
		
		"dtmSubjectResidenceStart" Date, 
		
		"dtmSubjectResidenceStop" Date, 
		
		"strSubjectResidenceCustom1" text, 
		
		"strSubjectResidenceCustom2" text, 
		
		"strSubjectResidenceCustom3" text, 
		
		"strSubjectResidenceVerification" text
	);

	create table "tblSubjectXSubject" (
	
	
	
		
		"lngSubjectID" integer, 
		
		"lngSubjectRelatedID" integer, 
		
		"lngRelationType" integer, 
		
		"blnRelationIsParent" boolean
	);

	create  index "lngAnswerID" 
		on "tblAnswer" ( 

		

			"lngAnswerID"

			
		);
		

	create  index "lngQuestionID" 
		on "tblAnswer" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "lngQuestionnaireID" 
		on "tblAnswer" ( 

		

			"lngQuestionnaireID"

			
		);
		

	create unique  index "APrimaryKey14" 
		on "tblAnswer" ( 

		

			"lngAnswerID"

			
		);
		

	create unique  index "relQ" 
		on "tblAnswer" ( 

		

			"lngQuestionnaireID"

			, 

			"lngQuestionID"

			
		);
		

	create  index "tblAnswerID" 
		on "tblAnswer" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "tblQuestionnaireXQuestiontblAnswer" 
		on "tblAnswer" ( 

		

			"lngQuestionnaireID"

			, 

			"lngQuestionID"

			
		);
		

	create  index "tblSubjecttblAnswer" 
		on "tblAnswer" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngAudioIncentiveID" 
		on "tblAudioPrompt" ( 

		

			"lngAudioPromptID"

			
		);
		

	create unique  index "PrimaryKey2" 
		on "tblAudioPrompt" ( 

		

			"lngAudioPromptID"

			
		);
		

	create  index "QlngQuestionID" 
		on "tblQuestion" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "QlngQuestionTypeID" 
		on "tblQuestion" ( 

		

			"lngQuestionTypeID"

			
		);
		

	create unique  index "PrimaryKey3" 
		on "tblQuestion" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "tblQuestionTypetblQuestion" 
		on "tblQuestion" ( 

		

			"lngQuestionTypeID"

			
		);
		

	create  index "QlngQuestionnaireID" 
		on "tblQuestionnaire" ( 

		

			"lngQuestionnaireID"

			
		);
		

	create unique  index "PrimaryKey4" 
		on "tblQuestionnaire" ( 

		

			"lngQuestionnaireID"

			
		);
		

	create  index "QstrQuestionnaireID" 
		on "tblQuestionnaire" ( 

		

			"strQuestionnaireName"

			
		);
		

	create  index "QXlngQuestionID" 
		on "tblQuestionnaireXQuestion" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "QQXlngQuestionnaireID" 
		on "tblQuestionnaireXQuestion" ( 

		

			"lngQuestionnaireID"

			
		);
		

	create unique  index "PrimaryKey5" 
		on "tblQuestionnaireXQuestion" ( 

		

			"lngQuestionnaireID"

			, 

			"lngQuestionID"

			
		);
		

	create  index "tblQuestionnairetblQuestionnaireXQuestion" 
		on "tblQuestionnaireXQuestion" ( 

		

			"lngQuestionnaireID"

			
		);
		

	create  index "tblQuestiontblQuestionnaireXQuestion" 
		on "tblQuestionnaireXQuestion" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "lngQuestionResponseOptionID" 
		on "tblQuestionResponseOption" ( 

		

			"lngQuestionResponseOptionID"

			
		);
		

	create unique  index "PrimaryKey6" 
		on "tblQuestionResponseOption" ( 

		

			"lngQuestionResponseOptionID"

			
		);
		

	create  index "lngQuestionTypeID" 
		on "tblQuestionType" ( 

		

			"lngQuestionTypeID"

			
		);
		

	create unique  index "PrimaryKey7" 
		on "tblQuestionType" ( 

		

			"lngQuestionTypeID"

			
		);
		

	create  index "lngQuestionID2" 
		on "tblQuestionXResponseOption" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "lngQuestionResponseOptionID2" 
		on "tblQuestionXResponseOption" ( 

		

			"lngQuestionResponseOptionID"

			
		);
		

	create unique  index "PrimaryKey8" 
		on "tblQuestionXResponseOption" ( 

		

			"lngQuestionID"

			, 

			"lngQuestionResponseOptionID"

			
		);
		

	create  index "tblQuestionResponseOptiontblQuestionXResponseOption" 
		on "tblQuestionXResponseOption" ( 

		

			"lngQuestionResponseOptionID"

			
		);
		

	create  index "tblQuestiontblQuestionXResponseOption" 
		on "tblQuestionXResponseOption" ( 

		

			"lngQuestionID"

			
		);
		

	create  index "lngSubjectID9" 
		on "tblSubject" ( 

		

			"lngSubjectID"

			
		);
		

	create unique  index "PrimaryKey9" 
		on "tblSubject" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngAudioIncentiveID2" 
		on "tblSubjectAudioFile" ( 

		

			"lngAudioIncentiveID"

			
		);
		

	create  index "lngSubjectAudioFileID" 
		on "tblSubjectAudioFile" ( 

		

			"lngSubjectAudioFileID"

			
		);
		

	create  index "lngSubjectID2" 
		on "tblSubjectAudioFile" ( 

		

			"lngSubjectID"

			
		);
		

	create unique  index "PrimaryKey10" 
		on "tblSubjectAudioFile" ( 

		

			"lngSubjectID"

			, 

			"lngSubjectAudioFileID"

			
		);
		

	create  index "tblAudioIncentivetblSubjectAudioFile" 
		on "tblSubjectAudioFile" ( 

		

			"lngAudioIncentiveID"

			
		);
		

	create  index "tblSubjecttblSubjectAudioFile" 
		on "tblSubjectAudioFile" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngAnswerID2" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngAnswerID"

			
		);
		

	create  index "lngSubjectAudioFileID2" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngSubjectAudioFileID"

			
		);
		

	create  index "lngSubjectID3" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngSubjectID"

			
		);
		

	create unique  index "PrimaryKey11" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngSubjectID"

			, 

			"lngSubjectAudioFileID"

			, 

			"lngAnswerID"

			
		);
		

	create  index "tblAnswertblSubjectAudioFileXAnswer" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngAnswerID"

			
		);
		

	create  index "tblSubjectAudioFiletblSubjectAudioFileXAnswer" 
		on "tblSubjectAudioFileXAnswer" ( 

		

			"lngSubjectID"

			, 

			"lngSubjectAudioFileID"

			
		);
		

	create  index "lngSubjectID4" 
		on "tblSubjectResidence" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngSubjectResidenceID" 
		on "tblSubjectResidence" ( 

		

			"lngSubjectResidenceID"

			
		);
		

	create unique  index "PrimaryKey12" 
		on "tblSubjectResidence" ( 

		

			"lngSubjectID"

			, 

			"lngSubjectResidenceID"

			
		);
		

	create  index "tblSubjecttblSubjectResidence" 
		on "tblSubjectResidence" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngSubjectID" 
		on "tblSubjectXSubject" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "lngSubjectRelatedID" 
		on "tblSubjectXSubject" ( 

		

			"lngSubjectRelatedID"

			
		);
		

	create unique  index "PrimaryKey13" 
		on "tblSubjectXSubject" ( 

		

			"lngSubjectID"

			, 

			"lngSubjectRelatedID"

			
		);
		

	create  index "tblSubjecttblSubjectXSubject" 
		on "tblSubjectXSubject" ( 

		

			"lngSubjectID"

			
		);
		

	create  index "tblSubjecttblSubjectXSubject1" 
		on "tblSubjectXSubject" ( 

		

			"lngSubjectRelatedID"

			
		);
		

	alter table "tblSubjectAudioFileXAnswer" add constraint 
	   "rel_tblAnswertblSubjectAudioFileXAnswer" foreign key (
	    
	      "lngAnswerID"
	      
	   )
	   references "tblAnswer" (
	    
	      "lngAnswerID"
	      	   
	   );

	alter table "tblSubjectAudioFile" add constraint 
	   "rel_tblAudioIncentivetblSubjectAudioFile" foreign key (
	    
	      "lngAudioIncentiveID"
	      
	   )
	   references "tblAudioPrompt" (
	    
	      "lngAudioPromptID"
	      	   
	   );

	alter table "tblQuestionnaireXQuestion" add constraint 
	   "rel_tblQuestionnairetblQuestionnaireXQuestion" foreign key (
	    
	      "lngQuestionnaireID"
	      
	   )
	   references "tblQuestionnaire" (
	    
	      "lngQuestionnaireID"
	      	   
	   );

	alter table "tblAnswer" add constraint 
	   "rel_tblQuestionnaireXQuestiontblAnswer" foreign key (
	    
	      "lngQuestionnaireID"
	      , 
	      "lngQuestionID"
	      
	   )
	   references "tblQuestionnaireXQuestion" (
	    
	      "lngQuestionnaireID"
	      , 
	      "lngQuestionID"
	      	   
	   );

	alter table "tblQuestionXResponseOption" add constraint 
	   "rel_tblQuestionResponseOptiontblQuestionXResponseOption" foreign key (
	    
	      "lngQuestionResponseOptionID"
	      
	   )
	   references "tblQuestionResponseOption" (
	    
	      "lngQuestionResponseOptionID"
	      	   
	   );

	alter table "tblQuestionnaireXQuestion" add constraint 
	   "rel_tblQuestiontblQuestionnaireXQuestion" foreign key (
	    
	      "lngQuestionID"
	      
	   )
	   references "tblQuestion" (
	    
	      "lngQuestionID"
	      	   
	   );

	alter table "tblQuestionXResponseOption" add constraint 
	   "rel_tblQuestiontblQuestionXResponseOption" foreign key (
	    
	      "lngQuestionID"
	      
	   )
	   references "tblQuestion" (
	    
	      "lngQuestionID"
	      	   
	   );

	alter table "tblQuestion" add constraint 
	   "rel_tblQuestionTypetblQuestion" foreign key (
	    
	      "lngQuestionTypeID"
	      
	   )
	   references "tblQuestionType" (
	    
	      "lngQuestionTypeID"
	      	   
	   );

	alter table "tblSubjectAudioFileXAnswer" add constraint 
	   "rel_tblSubjectAudioFiletblSubjectAudioFileXAnswer" foreign key (
	    
	      "lngSubjectID"
	      , 
	      "lngSubjectAudioFileID"
	      
	   )
	   references "tblSubjectAudioFile" (
	    
	      "lngSubjectID"
	      , 
	      "lngSubjectAudioFileID"
	      	   
	   );

	alter table "tblAnswer" add constraint 
	   "rel_tblSubjecttblAnswer" foreign key (
	    
	      "lngSubjectID"
	      
	   )
	   references "tblSubject" (
	    
	      "lngSubjectID"
	      	   
	   );

	alter table "tblSubjectAudioFile" add constraint 
	   "rel_tblSubjecttblSubjectAudioFile" foreign key (
	    
	      "lngSubjectID"
	      
	   )
	   references "tblSubject" (
	    
	      "lngSubjectID"
	      	   
	   );

	alter table "tblSubjectResidence" add constraint 
	   "rel_tblSubjecttblSubjectResidence" foreign key (
	    
	      "lngSubjectID"
	      
	   )
	   references "tblSubject" (
	    
	      "lngSubjectID"
	      	   
	   );

	alter table "tblSubjectXSubject" add constraint 
	   "rel_tblSubjecttblSubjectXSubject" foreign key (
	    
	      "lngSubjectID"
	      
	   )
	   references "tblSubject" (
	    
	      "lngSubjectID"
	      	   
	   );

	alter table "tblSubjectXSubject" add constraint 
	   "rel_tblSubjecttblSubjectXSubject1" foreign key (
	    
	      "lngSubjectRelatedID"
	      
	   )
	   references "tblSubject" (
	    
	      "lngSubjectID"
	      	   
	   );

