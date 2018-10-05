SELECT GENERATE_UUID() uuid FROM `doge-park.open_layer.export_audience` LIMIT 1000
-- SELECT ROW_NUMBER() OVER(PARTITION BY g) rn FROM `doge-park.open_layer.export_audience` LIMIT 1000