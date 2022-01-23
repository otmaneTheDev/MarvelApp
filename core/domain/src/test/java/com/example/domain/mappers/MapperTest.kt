package com.example.domain.mappers

import com.example.data.models.CharacterDataWrapperResponse
import com.example.domain.models.CharacterDataContainer
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class MapperTest {
    private val mapper = Mapper()

    @Test
    fun testMap() {
        val input = "{\n" +
                "  \"code\": 200,\n" +
                "  \"status\": \"Ok\",\n" +
                "  \"copyright\": \"© 2022 MARVEL\",\n" +
                "  \"attributionText\": \"Data provided by Marvel. © 2022 MARVEL\",\n" +
                "  \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2022 MARVEL</a>\",\n" +
                "  \"etag\": \"691083ac90d261568110aaaa09dfe55ccf7e0572\",\n" +
                "  \"data\": {\n" +
                "    \"offset\": 0,\n" +
                "    \"limit\": 20,\n" +
                "    \"total\": 1,\n" +
                "    \"count\": 1,\n" +
                "    \"results\": [{\n" +
                "      \"id\": 1009146,\n" +
                "      \"name\": \"Abomination (Emil Blonsky)\",\n" +
                "      \"description\": \"Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.\",\n" +
                "      \"modified\": \"2012-03-20T12:32:12-0400\",\n" +
                "      \"thumbnail\": {\n" +
                "        \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04\",\n" +
                "        \"extension\": \"jpg\"\n" +
                "      },\n" +
                "      \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009146\",\n" +
                "      \"comics\": {\n" +
                "        \"available\": 53,\n" +
                "        \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1009146/comics\",\n" +
                "        \"items\": [{\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/17547\",\n" +
                "          \"name\": \"Avengers (1998) #61\"\n" +
                "        }],\n" +
                "        \"returned\": 1\n" +
                "      },\n" +
                "      \"series\": {\n" +
                "        \"available\": 26,\n" +
                "        \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1009146/series\",\n" +
                "        \"items\": [{\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/354\",\n" +
                "          \"name\": \"Avengers (1998 - 2004)\"\n" +
                "        }, {\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/158\",\n" +
                "          \"name\": \"Avengers Vol. 1: World Trust (2003)\"\n" +
                "        }],\n" +
                "        \"returned\": 2\n" +
                "      },\n" +
                "      \"stories\": {\n" +
                "        \"available\": 63,\n" +
                "        \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1009146/stories\",\n" +
                "        \"items\": [{\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/4946\",\n" +
                "          \"name\": \"4 of 4 - 4XLS\",\n" +
                "          \"type\": \"cover\"\n" +
                "        }, {\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/5496\",\n" +
                "          \"name\": \"Irredeemable Ant-Man (2006) #1\",\n" +
                "          \"type\": \"cover\"\n" +
                "        }],\n" +
                "        \"returned\": 20\n" +
                "      },\n" +
                "      \"events\": {\n" +
                "        \"available\": 1,\n" +
                "        \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1009146/events\",\n" +
                "        \"items\": [{\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/events/296\",\n" +
                "          \"name\": \"Chaos War\"\n" +
                "        }],\n" +
                "        \"returned\": 1\n" +
                "      },\n" +
                "      \"urls\": [{\n" +
                "        \"type\": \"detail\",\n" +
                "        \"url\": \"http://marvel.com/characters/81/abomination?utm_campaign=apiRef&utm_source=94f57e588fce1a284d91bf0caf4f4d5c\"\n" +
                "      }, {\n" +
                "        \"type\": \"wiki\",\n" +
                "        \"url\": \"http://marvel.com/universe/Abomination?utm_campaign=apiRef&utm_source=94f57e588fce1a284d91bf0caf4f4d5c\"\n" +
                "      }, {\n" +
                "        \"type\": \"comiclink\",\n" +
                "        \"url\": \"http://marvel.com/comics/characters/1009146/abomination_emil_blonsky?utm_campaign=apiRef&utm_source=94f57e588fce1a284d91bf0caf4f4d5c\"\n" +
                "      }]\n" +
                "    }]\n" +
                "  }\n" +
                "}"

        val response = Gson().fromJson(input, CharacterDataWrapperResponse::class.java)
        val result = mapper.map(response.characterDataContainer)

        Assert.assertEquals(result::class.java, CharacterDataContainer::class.java)
        Assert.assertEquals(0, result.offset)
        Assert.assertEquals(20, result.limit)
        Assert.assertEquals(1, result.total)
        Assert.assertEquals(1, result.count)
        // Test all other fields
    }
}