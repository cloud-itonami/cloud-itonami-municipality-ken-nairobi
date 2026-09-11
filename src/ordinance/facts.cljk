(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Nairobi -- the
  THIRTIETH municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon,
  -ind-new-delhi, -pol-warsaw for the first twenty-nine) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation). The
  axis's first Sub-Saharan African entry alongside
  cloud-itonami-municipality-zaf-cape-town (South Africa).

  nairobi.go.ke returned a whole-domain TLS certificate error
  ('unable to verify the first certificate') on every URL tried,
  matching the same failure class hit earlier this session for
  Chile's transparencia.munistgo.cl and Bolivia's
  economiayfinanzas.gob.bo -- abandoned outright. kenyalaw.org (the
  national legal-reporting body) returned HTTP 403 site-wide, and its
  own metadata for the Finance Act 2013 was internally inconsistent
  across mirrors (one source said 'Act No. 2 of 2013', another said
  'Act No. 1b of 2013'; commencement dates conflicted too) -- NOT used
  for that reason, per this project's no-fabrication discipline.

  Both entries here instead cite two working, mutually-corroborating
  sources: ecolex.org (a joint FAO/UNEP/IUCN international
  environmental-law database) directly confirms the Solid Waste
  Management Act's title, number, and Kenya Gazette Supplement
  publication date; nairobiassembly.go.ke (the County Assembly's own
  PDF hosting, a different domain than the TLS-broken nairobi.go.ke)
  hosts the Finance Act 2023's own Kenya Gazette Supplement text,
  which rendered fully legibly via the Read-on-saved-path fallback
  and states its own Date of Assent directly.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"nairobi"
   [{:ordinance/id "nairobi.solid-waste-management-act-2015"
     :ordinance/title "Nairobi City County Solid Waste Management Act, 2015"
     :ordinance/municipality "nairobi"
     :ordinance/country "KEN"
     :ordinance/kind :ordinance
     :ordinance/number "No. 5 of 2015"
     :ordinance/url "https://www.ecolex.org/details/legislation/nairobi-city-county-solid-waste-management-act-no-5-of-2015-lex-faoc162337/"
     :ordinance/url-provenance :official-ecolex-mirror
     :ordinance/enacted-date "2015-10-22"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:waste-management :environment}}
    {:ordinance/id "nairobi.finance-act-2023"
     :ordinance/title "The Nairobi City County Finance Act, 2023"
     :ordinance/municipality "nairobi"
     :ordinance/country "KEN"
     :ordinance/kind :ordinance
     :ordinance/number "No. 4 of 2023"
     :ordinance/url "https://nairobiassembly.go.ke/ncca/wp-content/uploads/act/2023/The-Nairobi-City-County-Finance-Act-2023.pdf"
     :ordinance/url-provenance :official-nairobiassembly-go-ke
     :ordinance/enacted-date "2023-10-13"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:taxation}}]})

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
      :note (str "cloud-itonami-municipality-ken-nairobi Wave 0 (ADR-2607141700): "
                 (count (get catalog "nairobi")) " Nairobi entries seeded "
                 "with official ecolex.org/nairobiassembly.go.ke citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
