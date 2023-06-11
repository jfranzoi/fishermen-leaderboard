package my.projects.fishermenleaderboard.api.integration;

import my.projects.fishermenleaderboard.api.integration.dto.OgyreFishermen;
import my.projects.fishermenleaderboard.api.integration.dto.OgyreRecollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class OgyreExamClientTest {
    private RestTemplate restTemplate;
    private MockRestServiceServer server;

    @BeforeEach
    void setUp() {
        restTemplate = new RestOperationsCreator("https://my.local/api/exam/", "my-secret").create();
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @AfterEach
    void tearDown() {
        server.verify();
    }

    @Test
    void authenticated() {
        server.expect(requestTo("https://my.local/api/exam/fishermen"))
                .andExpect(header("Authorization", "Bearer my-secret"))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON).body("[]"));

        new OgyreExamClient(restTemplate).fishermen();
    }

    @Test
    void collectFishermen() {
        server.expect(requestTo("https://my.local/api/exam/fishermen")).andRespond(withSuccess()
                .contentType(MediaType.APPLICATION_JSON)
                .body("[    {\n" +
                        "        \"plasticRecovers\": [],\n" +
                        "        \"_id\": \"61eacd09010d36584891e10a\",\n" +
                        "        \"name\": \"Fabrizio\",\n" +
                        "        \"surname\": \"Benvenuto\",\n" +
                        "        \"port\": \"Santa Margherita Ligure\",\n" +
                        "        \"country\": \"Italy\",\n" +
                        "        \"vessel_code\": \"-\",\n" +
                        "        \"ongID\": {\n" +
                        "            \"_id\": \"61eab1a3010d36584891e00d\",\n" +
                        "            \"email\": \"stella@ogyre.com\",\n" +
                        "            \"name\": \"Stella - SANTA MARGHERITA\",\n" +
                        "            \"country\": \"Italia\",\n" +
                        "            \"admin\": false,\n" +
                        "            \"createdAt\": \"2022-01-21T13:14:11.719Z\",\n" +
                        "            \"updatedAt\": \"2022-02-24T13:37:40.539Z\",\n" +
                        "            \"__v\": 0,\n" +
                        "            \"address\": \"\",\n" +
                        "            \"website\": \"\"\n" +
                        "        },\n" +
                        "        \"fishermanPicture\": \"https://ogyre.fra1.digitaloceanspaces.com/1665499192939-xs6c\",\n" +
                        "        \"experience\": \"27\",\n" +
                        "        \"description\": \"Fabrizio Benvenuto initially started working in a workshop, but since he spent every spare moment fishing in 1995 he quit his job to buy a fishing boat.\\r\\nIn 2015 he got a small trawler (Ermete) with which he retrieves kilograms of litter every day. Before, he was forced to throw them back into the water, but now, thanks to a matured environmental awareness and Ogyre helping him to dispose them properly, he gladly gives his contribution to protect the sea.\",\n" +
                        "        \"boatName\": \"Ermete\",\n" +
                        "        \"boatPicture\": \"https://ogyre.fra1.digitaloceanspaces.com/1643725070096-IMG_20220201_115642.jpeg\",\n" +
                        "        \"createdAt\": \"2022-01-21T15:11:05.141Z\",\n" +
                        "        \"updatedAt\": \"2023-05-26T13:52:32.780Z\",\n" +
                        "        \"__v\": 0,\n" +
                        "        \"extraFishermanName\": \"\",\n" +
                        "        \"extraFishermanSurname\": \"\",\n" +
                        "        \"lastTransaction\": \"2022-04-12T18:04:04.922Z\",\n" +
                        "        \"description_ita\": \"Fabrizio Benvenuto ha cominciato a lavorare in carrozzeria, ma dato che ogni momento libero lo passava a pescare nel 1995 ha lasciato il lavoro per comprarsi un gozzo da pesca.\\r\\nNel 2015 ha preso un piccolo peschereccio a strascico (Ermete) con cui recupera ogni giorno kg di rifiuti. Prima era costretto a rigettarli in acqua, ma ora, grazie a una maturata sensibilità ambientale e Ogyre che lo aiuta a smarltirli correttamente, dà volentieri il suo contributo per proteggere il mare.\",\n" +
                        "        \"enabled\": true,\n" +
                        "        \"portID\": \"6470b61e4ae0d1ec7fd65441\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"plasticRecovers\": [],\n" +
                        "        \"_id\": \"61f14d6026713dce105556d3\",\n" +
                        "        \"name\": \"Charles\",\n" +
                        "        \"surname\": \"de Carvalho da Silva Cruz \",\n" +
                        "        \"port\": \"Rio de Janeiro \",\n" +
                        "        \"country\": \"Brazil\",\n" +
                        "        \"vessel_code\": \"\",\n" +
                        "        \"ongID\": {\n" +
                        "            \"_id\": \"61f1463f26713dce105556b8\",\n" +
                        "            \"email\": \"pedro.succar@bvrio.org\",\n" +
                        "            \"name\": \"BvRio\",\n" +
                        "            \"country\": \"Brazil\",\n" +
                        "            \"admin\": false,\n" +
                        "            \"createdAt\": \"2022-01-26T13:01:51.071Z\",\n" +
                        "            \"updatedAt\": \"2022-01-26T13:01:51.071Z\",\n" +
                        "            \"__v\": 0\n" +
                        "        },\n" +
                        "        \"fishermanPicture\": \"https://ogyre.fra1.digitaloceanspaces.com/1650226099045-cropped.jpeg\",\n" +
                        "        \"experience\": \"25\",\n" +
                        "        \"description\": \"Charles is 36 years old, he is married and has 4 children. He has been fishing since he was 10 years old and takes great pride in being able to protect his ocean. \",\n" +
                        "        \"boatName\": \"Quero Mamãe\",\n" +
                        "        \"boatPicture\": \"https://ogyre.fra1.digitaloceanspaces.com/1643203936448-WhatsApp Image 2022-01-26 at 10.30.30.jpeg\",\n" +
                        "        \"extraFishermanName\": \"\",\n" +
                        "        \"extraFishermanSurname\": \"\",\n" +
                        "        \"createdAt\": \"2022-01-26T13:32:16.488Z\",\n" +
                        "        \"updatedAt\": \"2022-08-22T14:15:14.461Z\",\n" +
                        "        \"__v\": 0,\n" +
                        "        \"lastTransaction\": \"2022-04-13T09:29:50.627Z\",\n" +
                        "        \"enabled\": true,\n" +
                        "        \"description_ita\": \"Charles ha 36 anni, è sposato e ha 4 figli. Pesca da quando aveva 10 anni ed è molto orgoglioso di poter proteggere il suo mare. \"\n" +
                        "    }]")
        );

        List<OgyreFishermen> response = new OgyreExamClient(restTemplate).fishermen();

        List<String> ids = response.stream()
                .map(it -> it._id)
                .collect(Collectors.toList());
        assertThat(ids, contains(
                "61eacd09010d36584891e10a",
                "61f14d6026713dce105556d3"
        ));

        List<String> pictures = response.stream()
                .map(it -> it.fishermanPicture.toString())
                .collect(Collectors.toList());
        assertThat(pictures, everyItem(startsWith("https://ogyre.fra1.digitaloceanspaces.com/")));
    }

    @Test
    void collectRecollections() {
        server.expect(requestTo("https://my.local/api/exam/recollections/61eacd09010d36584891e10a")).andRespond(withSuccess()
                .contentType(MediaType.APPLICATION_JSON).body("{\n" +
                        "    \"listOfrecollections\": [\n" +
                        "        {\n" +
                        "            \"_id\": \"646c80c0a0a7f5da7666c68c\",\n" +
                        "            \"kg\": 8.5,\n" +
                        "            \"fishermanID\": \"61eacd09010d36584891e10a\",\n" +
                        "            \"ongID\": \"61eab1a3010d36584891e00d\",\n" +
                        "            \"date\": \"2023-05-18T00:00:00.000Z\",\n" +
                        "            \"pictureRecollection\": \"https://ogyre.fra1.digitaloceanspaces.com/1684832448022-0z52\",\n" +
                        "            \"kg_availables\": 8.5,\n" +
                        "            \"status\": \"available\",\n" +
                        "            \"createdAt\": \"2023-05-23T09:00:48.029Z\",\n" +
                        "            \"updatedAt\": \"2023-05-24T16:30:01.268Z\",\n" +
                        "            \"__v\": 0\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"_id\": \"646c80e9a0a7f5da7666c697\",\n" +
                        "            \"kg\": 4.5,\n" +
                        "            \"fishermanID\": \"61eacd09010d36584891e10a\",\n" +
                        "            \"ongID\": \"61eab1a3010d36584891e00d\",\n" +
                        "            \"date\": \"2023-05-18T00:00:00.000Z\",\n" +
                        "            \"pictureRecollection\": \"https://ogyre.fra1.digitaloceanspaces.com/1684832489944-xuyz\",\n" +
                        "            \"kg_availables\": 4.5,\n" +
                        "            \"status\": \"available\",\n" +
                        "            \"createdAt\": \"2023-05-23T09:01:29.958Z\",\n" +
                        "            \"updatedAt\": \"2023-05-24T16:30:01.268Z\",\n" +
                        "            \"__v\": 0\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"_id\": \"647606ec90b6fb6946b694fb\",\n" +
                        "            \"kg\": 11.3,\n" +
                        "            \"fishermanID\": \"61eacd09010d36584891e10a\",\n" +
                        "            \"ongID\": \"61eab1a3010d36584891e00d\",\n" +
                        "            \"date\": \"2023-05-24T00:00:00.000Z\",\n" +
                        "            \"pictureRecollection\": \"https://ogyre.fra1.digitaloceanspaces.com/1685456620644-pozv\",\n" +
                        "            \"kg_availables\": 0,\n" +
                        "            \"status\": \"sold\",\n" +
                        "            \"createdAt\": \"2023-05-30T14:23:40.648Z\",\n" +
                        "            \"updatedAt\": \"2023-06-02T15:00:01.896Z\",\n" +
                        "            \"__v\": 0\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"
                ));

        List<OgyreRecollection> response = new OgyreExamClient(restTemplate).recollections("61eacd09010d36584891e10a");

        List<BigDecimal> amounts = response.stream()
                .map(it -> it.kg)
                .collect(Collectors.toList());
        assertThat(amounts, contains(
                new BigDecimal("8.5"),
                new BigDecimal("4.5"),
                new BigDecimal("11.3")
        ));

        List<ZonedDateTime> dates = response.stream()
                .map(it -> it.date)
                .collect(Collectors.toList());
        assertThat(dates, contains(
                ZonedDateTime.parse("2023-05-18T00:00Z[UTC]"),
                ZonedDateTime.parse("2023-05-18T00:00Z[UTC]"),
                ZonedDateTime.parse("2023-05-24T00:00Z[UTC]")
        ));

        List<String> pictures = response.stream()
                .map(it -> it.pictureRecollection.toString())
                .collect(Collectors.toList());
        assertThat(pictures, everyItem(startsWith("https://ogyre.fra1.digitaloceanspaces.com/")));
    }
}