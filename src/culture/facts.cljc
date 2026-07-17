(ns culture.facts
  "Regional-culture catalog for Nairobi -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"nairobi"
   [{:culture/id "nairobi.dish.nyama-choma"
     :culture/name "Nyama choma"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :dish
     :culture/summary "Grilled or barbecued goat or beef, considered the national dish of Kenya and Tanzania, with strong cultural significance in Nairobi and East Africa."
     :culture/url "https://en.wikipedia.org/wiki/Nyama_choma"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.dish.ugali"
     :culture/name "Ugali"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :dish
     :culture/summary "Maize-flour meal cooked to a firm dough-like consistency, a staple starch in Kenya and much of Africa, eaten with vegetables or meat."
     :culture/url "https://en.wikipedia.org/wiki/Ugali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.dish.sukuma-wiki"
     :culture/name "Sukuma wiki"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :dish
     :culture/summary "East African dish of collard greens cooked with onions and spices, common in Kenya, Tanzania, Uganda and other parts of East Africa."
     :culture/url "https://en.wikipedia.org/wiki/Sukuma_wiki"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.dish.githeri"
     :culture/name "Githeri"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :dish
     :culture/summary "Traditional Kenyan meal of maize kernels and beans boiled together, the staple food of ethnic groups of Kenya's Central and Eastern provinces including the Gikuyu, Meru, Mbeere and Embu."
     :culture/url "https://en.wikipedia.org/wiki/Githeri"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.dish.mutura"
     :culture/name "Mutura"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :dish
     :culture/summary "Traditional Kenyan blood sausage of minced meat, tripe and animal blood, commonly consumed as street food in Kenya."
     :culture/url "https://en.wikipedia.org/wiki/Mutura"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.product.kenyan-coffee"
     :culture/name "Kenyan coffee"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :product
     :culture/summary "Coffee from Kenya, well known for its intense flavor, full body and pleasant aroma with notes of cocoa; sales are traded through the Nairobi Coffee Exchange."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_production_in_Kenya"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.heritage.nairobi-national-park"
     :culture/name "Nairobi National Park"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :heritage
     :culture/summary "National park established in 1946 about 7 km south of Nairobi, the first national park in Kenya."
     :culture/url "https://en.wikipedia.org/wiki/Nairobi_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nairobi.heritage.karen-blixen-museum"
     :culture/name "Karen Blixen Museum"
     :culture/municipality "nairobi"
     :culture/country "KEN"
     :culture/kind :heritage
     :culture/summary "Former African home of Danish author Karen Blixen, in the Karen suburb of Nairobi near the Ngong Hills."
     :culture/url "https://en.wikipedia.org/wiki/Karen_Blixen_Museum_(Kenya)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ken-nairobi culture catalog "
                 "(ADR-2607171400): " (count (get catalog "nairobi"))
                 " Nairobi entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
