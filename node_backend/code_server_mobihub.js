exports.bikes = function(req, res){
  res.json('[{"lat":-23.581660,"lng":-46.657675},{"lat":-23.5505199,"lng":-46.6333094},{"lat":-23.6472564,"lng":-46.6300535},{"lat":-23.6473261,"lng":-46.6302029},{"lat":-23.6471653,"lng":-46.6309561},{"lat":-23.6447623,"lng":-46.6297209},{"lat":-23.645871,"lng":-46.632113},{"lat":-23.6485822,"lng":-46.6301374},{"lat":-23.6471714,"lng":-46.6322035},{"lat":-23.6457458,"lng":-46.6321898},{"lat":-23.6457861,"lng":-46.6325254},{"lat":-23.6460166,"lng":-46.6327998},{"lat":-23.6465029,"lng":-46.6298996},{"lat":-23.6471013,"lng":-46.6303164},{"lat":-23.6457856,"lng":-46.6297229},{"lat":-23.647549,"lng":-46.629358},{"lat":-23.6478347,"lng":-46.6304001},{"lat":-23.6474824,"lng":-46.6312407},{"lat":-23.648104,"lng":-46.630636},{"lat":-23.6466701,"lng":-46.6318825},{"lat":-23.637181,"lng":-46.6361611}]');
}

exports.directions = function(req, res){
  var opcoes = ["walking", "bicycling", "transit", "transit+bike", "uber"];
  var origin = req.query.origin;
  var destination = req.query.destination;
  var count_walks=0, count_processed=0;
  var array_de_rotas=[];
  
  opcoes.forEach(function(rota){
    if(rota == "transit+bike"){
      var preco_total=0;
      var tempo_total=0;
      var url = "https://maps.googleapis.com/maps/api/directions/json?"+
            "origin="+origin+ 
            "&destination="+destination+ 
            "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
            "&mode=transit";
      count_walks++;
      request(url, function (error, resp, body) {
        var array_json_tratado = [];
        var json = JSON.parse(body);
        if(json.status == 'OK'){
          if(json.routes[0]){
            json.routes[0].legs[0].steps.forEach(function(step){
              if(step.travel_mode == 'WALKING'){
                var start = step.start_location;
                var end = step.end_location;
                var distancia = Math.sqrt(Math.pow(Math.round(100000000*(end.lat - start.lat))/100000000, 2)+
                                          Math.pow(Math.round(100000000*(end.lng - start.lng))/100000000, 2));
                var bikes = [{"lat":-23.581660,"lng":-46.657675},{"lat":-23.5505199,"lng":-46.6333094},{"lat":-23.6472564,"lng":-46.6300535},{"lat":-23.6473261,"lng":-46.6302029},{"lat":-23.6471653,"lng":-46.6309561},{"lat":-23.6447623,"lng":-46.6297209},{"lat":-23.645871,"lng":-46.632113},{"lat":-23.6485822,"lng":-46.6301374},{"lat":-23.6471714,"lng":-46.6322035},{"lat":-23.6457458,"lng":-46.6321898},{"lat":-23.6457861,"lng":-46.6325254},{"lat":-23.6460166,"lng":-46.6327998},{"lat":-23.6465029,"lng":-46.6298996},{"lat":-23.6471013,"lng":-46.6303164},{"lat":-23.6457856,"lng":-46.6297229},{"lat":-23.647549,"lng":-46.629358},{"lat":-23.6478347,"lng":-46.6304001},{"lat":-23.6474824,"lng":-46.6312407},{"lat":-23.648104,"lng":-46.630636},{"lat":-23.6466701,"lng":-46.6318825},{"lat":-23.637181,"lng":-46.6361611}];
                var bike_min;
                bikes.forEach(function(bike){
                  var dist_bike = Math.sqrt(Math.pow(Math.round(100000000*(bike.lat - start.lat))/100000000, 2)+
                                            Math.pow(Math.round(100000000*(bike.lng - start.lng))/100000000, 2));
                  if(dist_bike < (distancia/2)){
                    if(!bike_min){
                      bike_min= {};
                      bike_min.dist = dist_bike;
                      bike_min.coord = bike;
                    }else if(bike_min.dist > dist_bike){
                      bike_min.dist = dist_bike;
                      bike_min.coord = bike;
                    }
                  }
                });
                if(bike_min){
                  // rota andando até a bike
                  var url_walking = "https://maps.googleapis.com/maps/api/directions/json?"+
                      "origin="+step.start_location+ 
                      "&destination="+bike_min.lat+","+bike_min.lng+ 
                      "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
                      "&mode=walking";
                  count_walks++;
                  request(url_walking, function (error2, resp2, body2) {
                    var json_ape = JSON.parse(body2);
                    var json_tratado = {};
                    if(json_ape.routes[0]){
                      json_ape.routes[0].legs[0].steps.forEach(function(step2){
                        json_tratado.tipo = step2.travel_mode;
                        json_tratado.cor = '#4550c1';
                        var result = [];
                        polyline.decode(step2.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                        json_tratado.polyline = result;
                        json_tratado.distance = step2.distance; 
                        json_tratado.duration = step2.duration; 
                        tempo_total+=step2.duration.value;
                      });
                    }else{console.log(body2+" - "+url_walking);}
                    count_processed++;
                    array_json_tratado.push(json_tratado);
                  });
                  // rota de bike até a estação
                  var url_bike = "https://maps.googleapis.com/maps/api/directions/json?"+
                      "origin="+step.start_location+ 
                      "&destination="+bike_min.lat+","+bike_min.lng+ 
                      "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
                      "&mode=bicycling";
                  count_walks++;
                  request(url_bike, function (error3, resp3, body3) {
                    var json_ape = JSON.parse(body3);
                    var json_tratado = {};
                    if(json_ape.routes[0]){
                      json_ape.routes[0].legs[0].steps.forEach(function(step3){
                        json_tratado.tipo = step3.travel_mode;
                        json_tratado.cor = '#4550c1';
                        var result = [];
                        polyline.decode(step3.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                        json_tratado.polyline = result;
                        json_tratado.distance = step3.distance; 
                        json_tratado.duration = step3.duration; 
                        tempo_total+=step3.duration.value;
                      });
                    }else{console.log(json_ape+" - "+resp3+" - "+error3);}
                    count_processed++;
                    array_json_tratado.push(json_tratado);
                  });
                }else{ // ignora a bike e vai andando mesmo
                  var json_tratado = {};
                  json_tratado.tipo = step.travel_mode;
                  json_tratado.cor = '#4550c1';
                  var result = [];
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.polyline = result;
                  json_tratado.distance = step.distance; 
                  json_tratado.duration = step.duration;
                  tempo_total+=step.duration.value;
                  array_json_tratado.push(json_tratado);
                }
              }else if(step.travel_mode == 'TRANSIT'){
                var json_tratado = {};
                var result = [];
                if(step.transit_details.line.vehicle.type == "BUS"){
                  json_tratado.tipo = step.transit_details.line.vehicle.type;
                  json_tratado.nome = step.transit_details.line.name;
                  json_tratado.numero = step.transit_details.line.short_name;
                  json_tratado.cor = step.transit_details.line.color;
                  json_tratado.paradas = step.transit_details.num_stops;
                  tempo_total+= step.transit_details.arrival_time.value - step.transit_details.departure_time.value;
                  preco_total+=4;
                  json_tratado.preco=4;
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.hora_saida = step.transit_details.departure_time;
                  json_tratado.hora_chegada = step.transit_details.arrival_time;
                }else if(step.transit_details.line.vehicle.type == "SUBWAY"){
                  json_tratado.tipo = step.transit_details.line.vehicle.type;
                  json_tratado.nome = step.transit_details.line.name;
                  json_tratado.numero = step.transit_details.line.short_name;
                  json_tratado.cor = step.transit_details.line.color;
                  json_tratado.paradas = step.transit_details.num_stops;
                  tempo_total+= step.transit_details.arrival_time.value - step.transit_details.departure_time.value;
                  preco_total+=4;
                  json_tratado.preco=4;
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.hora_saida = step.transit_details.departure_time;
                  json_tratado.hora_chegada = step.transit_details.arrival_time;
                }
                json_tratado.polyline = result;
                array_json_tratado.push(json_tratado);
              }
            });
          }
          array_de_rotas.push({preco_total: preco_total, tempo_total: tempo_total, tipo_rota: rota, array: array_json_tratado});
        }else{
          res.send(json);
        }
        count_processed++;
      });
    }else if(rota == "walking"){
      var preco_total=0;
      var tempo_total=0;
      var array_json_tratado = [];
      var url = "https://maps.googleapis.com/maps/api/directions/json?"+
            "origin="+origin+ 
            "&destination="+destination+ 
            "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
            "&mode="+rota;
      count_walks++;
      request(url, function (error2, resp2, body2) {
        var json_ape = JSON.parse(body2);
        var json_tratado = {};
        var result = [];
        if(json_ape.routes[0]){
          json_ape.routes[0].legs[0].steps.forEach(function(step2){
            json_tratado.tipo = step2.travel_mode;
            json_tratado.cor = '#4550c1';
            polyline.decode(step2.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
            json_tratado.distance = step2.distance; 
            json_tratado.duration = step2.duration; 
            tempo_total+=step2.duration.value;
          });
        }else{console.log(json_ape.routes[0]);}
        json_tratado.polyline = result;
        count_processed++;
        array_json_tratado.push(json_tratado);
        array_de_rotas.push({preco_total: preco_total, tempo_total: tempo_total, tipo_rota: rota, array: array_json_tratado});
      });
      
    }else if(rota == "bicycling"){
      var preco_total=0;
      var tempo_total=0;
      var url = "https://maps.googleapis.com/maps/api/directions/json?"+
            "origin="+origin+ 
            "&destination="+destination+ 
            "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
            "&mode="+rota;
      var array_json_tratado = [];
      count_walks++;
      request(url, function (error2, resp2, body2) {
        var json_ape = JSON.parse(body2);
        var json_tratado = {};
        var result = [];
        if(json_ape.routes[0]){
          json_ape.routes[0].legs[0].steps.forEach(function(step2){
            json_tratado.tipo = step2.travel_mode;
            json_tratado.cor = '#4550c1';
            polyline.decode(step2.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
            json_tratado.distance = step2.distance; 
            json_tratado.duration = step2.duration; 
            tempo_total+=step2.duration.value;
            //console.log(tempo_total);
            preco_total += Math.round(step2.duration.value/900);
          });
        }else{console.log(json_ape.routes[0]);}
        json_tratado.polyline = result;
        count_processed++;
        array_json_tratado.push(json_tratado);
        array_de_rotas.push({preco_total: preco_total, tempo_total: tempo_total, tipo_rota: rota, array: array_json_tratado});
      });
    }else if(rota =="transit"){
      var preco_total=0;
      var tempo_total=0;
      var url = "https://maps.googleapis.com/maps/api/directions/json?"+
            "origin="+origin+ 
            "&destination="+destination+ 
            "&key=AIzaSyCzbD-j9HiJdGnjc-EUU-lVMvpEp99e6j4"+
            "&mode=transit";
      count_walks++;
      request(url, function (error, resp, body) {
        var array_json_tratado = [];
        var json = JSON.parse(body);
        if(json.status == 'OK'){
          if(json.routes[0]){
            json.routes[0].legs[0].steps.forEach(function(step){
              if(step.travel_mode == 'WALKING'){// ignora a bike e vai andando mesmo
                  var json_tratado = {};
                  json_tratado.tipo = step.travel_mode;
                  json_tratado.cor = '#4550c1';
                  var result = [];
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.polyline = result;
                  json_tratado.distance = step.distance; 
                  json_tratado.duration = step.duration;
                  tempo_total+=step.duration.value;
                  array_json_tratado.push(json_tratado);
              }else if(step.travel_mode == 'TRANSIT'){
                var json_tratado = {};
                var result = [];
                if(step.transit_details.line.vehicle.type == "BUS"){
                  json_tratado.tipo = step.transit_details.line.vehicle.type;
                  json_tratado.nome = step.transit_details.line.name;
                  json_tratado.numero = step.transit_details.line.short_name;
                  json_tratado.cor = step.transit_details.line.color;
                  json_tratado.paradas = step.transit_details.num_stops;
                  tempo_total+= step.transit_details.arrival_time.value - step.transit_details.departure_time.value;
                  json_tratado.preco = 4;
                  preco_total+=4;
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.hora_saida = step.transit_details.departure_time;
                  json_tratado.hora_chegada = step.transit_details.arrival_time;
                }else if(step.transit_details.line.vehicle.type == "SUBWAY"){
                  json_tratado.tipo = step.transit_details.line.vehicle.type;
                  json_tratado.nome = step.transit_details.line.name;
                  json_tratado.numero = step.transit_details.line.short_name;
                  json_tratado.cor = step.transit_details.line.color;
                  json_tratado.paradas = step.transit_details.num_stops;
                  tempo_total+= step.transit_details.arrival_time.value - step.transit_details.departure_time.value;
                  json_tratado.preco = 4;
                  preco_total+=4;
                  polyline.decode(step.polyline.points).forEach(function(poli){result.push({"lat": poli[0], "lng": poli[1]});});
                  json_tratado.hora_saida = step.transit_details.departure_time;
                  json_tratado.hora_chegada = step.transit_details.arrival_time;
                }
                json_tratado.polyline = result;
                array_json_tratado.push(json_tratado);
              }
            });
          }
          array_de_rotas.push({preco_total: preco_total, tempo_total: tempo_total, tipo_rota: rota, array: array_json_tratado});
        }else{
          res.send(json);
        }
        count_processed++;
      });
    }else if(rota == "uber"){
      var url = encodeURI('https://api.uber.com/v1.2/estimates/price?start_latitude='+origin.lat+'&start_longitude='+origin.lng+
                  '&end_latitude='+destination.lat+'&end_longitude='+destination.lng);
      var options = {
        url: url,
        headers: {
          'Authorization': 'Token Rc2CoV0hYkpBxWJ-Vsn6lWOdppaDbUM4soUmEIsx',
          'Accept-Language': 'en_US',
          'Content-Type': 'application/json'
        }
      };
      count_walks++;
      request(options, function(err, resp, body){
        if (!err && resp.statusCode == 200) {
          var info = JSON.parse(body);
          console.log(info);
          var json_tratado ={};
          var array_json_tratado = [];
          //array_json_tratado.push(json_tratado);
          //array_de_rotas.push({preco_total: preco_total, tempo_total: tempo_total, tipo_rota: rota, array: array_json_tratado});
        }else{console.log(err+"- "+resp.statusCode+" - "+url);}
        count_processed++;
      });
    }
  });
  
  var intervalId = null;
  console.log(count_walks+" - "+count_processed);
  var loop = function(){
    console.log(count_walks+" - "+count_processed);
    if(count_walks == count_processed){
      res.send(array_de_rotas);
      clearInterval(intervalId);
    }
  };
  intervalId = setInterval(loop, 1000);
}