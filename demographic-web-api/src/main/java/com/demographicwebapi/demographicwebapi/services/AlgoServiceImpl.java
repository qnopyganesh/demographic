package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographicwebapi.demographicwebapi.OtherAlgorithms.Metaphone3;
import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.repositories.AlgoRepo;
import com.google.gson.Gson;
import org.apache.commons.codec.language.Caverphone1;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Nysiis;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;

@Service
public class AlgoServiceImpl implements AlgoService {
	@Autowired
	private AlgoRepo algoRepo;

	@Override
	public Algo getAlgo(String name) {
		return algoRepo.findByName(name).get(0);
	}

	@Override
	public String encodeString(String name, String algoName) {
		Gson gson = new Gson();
		String encodedName = "";
		algoName = algoName.toLowerCase();
		System.out.println("algo aname: " + algoName);
		if (algoName.equals("metaphone3")) {
			
			Metaphone3 encoder = new Metaphone3();
			encoder.SetWord(name);
			encoder.SetEncodeVowels(true);
			encoder.SetEncodeExact(true);
			encoder.Encode();
			encodedName = encoder.GetMetaph();
		} else if (algoName.equals("caverphone1")) {
			Caverphone1 encoder = new Caverphone1();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("caverphone2")) {
			Caverphone2 encoder = new Caverphone2();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("colognephonetic")) {
			ColognePhonetic encoder = new ColognePhonetic();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("daitchmokotoffsoundex")) {
			DaitchMokotoffSoundex encoder = new DaitchMokotoffSoundex();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("doublemetaphone")) {
			DoubleMetaphone encoder = new DoubleMetaphone();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("matchratingapproachencoder")) {
			MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("metaphone")) {
			Metaphone encoder = new Metaphone();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("nysiis")) {
			Nysiis encoder = new Nysiis();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("refinedsoundex")) {
			RefinedSoundex encoder = new RefinedSoundex();
			encodedName = encoder.encode(name);
		}

		else if (algoName.equals("soundex")) {
			Soundex encoder = new Soundex();
			encodedName = encoder.encode(name);
		} else {
			encodedName = "NoAlogFound";
		}
		System.out.println("O/p:"+encodedName);
		return gson.toJson(encodedName);
	}
}
