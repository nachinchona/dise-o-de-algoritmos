// quickhull grafico https://editor.p5js.org/nachinchona/sketches/ZNDQ0AtIB

let points = [];
let hull = [];
let paso = 0;
let pasos = [];
let listo = false;

function setup() {
  createCanvas(500, 500);
  let buffer = 20;
  // generar puntos y ordenar
  for (let i = 0; i < 50; i++) {
    points.push(createVector(random(buffer, width - buffer), random(buffer, height - buffer)));
  }
  points.sort((a, b) => a.x - b.x);
  llamar();
}

function draw() {
  background(0);

  // dibujar puntos
  stroke(255);
  strokeWeight(8);
  for (let p of points) {
    point(p.x, p.y);
  }
  let button = createButton('Siguiente');
  button.position(0, 500);
  
  if (button != undefined) {
    button.mousePressed(() => {paso++;});
  }
  
  if (paso < pasos.length) {pasos[paso]()};
  if (paso > pasos.length) {paso = 0};
  
  if (listo && paso == pasos.length) {
    // ordenar el hull para dibujar
    let cx = 0, cy = 0;
    for (let p of hull) {
        cx += p.x;
        cy += p.y;
    }
    cx /= hull.length;
    cy /= hull.length;
    hull.sort((a, b) => {
      let angleA = Math.atan2(a.y - cy, a.x - cx);
      let angleB = Math.atan2(b.y - cy, b.x - cx);
      return angleA - angleB;
    });
    // dibujar
    stroke(0, 0, 255);
    fill(0, 0, 255, 50);
    beginShape();
    for (let p of hull) {
      vertex(p.x,p.y);
    }
    endShape(CLOSE);
  }
}

function llamar() {
  // llamada inicial a funcion recursiva segun segmentos
  P1 = points[0];
  P2 = points[49];
  hull.push(P1);
  hull.push(P2);
  points.splice(49,1);
  points.shift();
  let segmentos = segmentar(points,P1,P2);
  hull = hull.concat(encontrarHull(segmentos[0], P1, P2, '0'));
  hull = hull.concat(encontrarHull(segmentos[1], P2, P1, '1'));
  listo = true;
}

function crearFuncion(P,Q,puntoMasLejano,tipo) {
  let codigo;
  if (tipo == '0') {
    codigo = "stroke(0, 255, 0); line(P.x, P.y, puntoMasLejano.x, puntoMasLejano.y); line(Q.x, Q.y, puntoMasLejano.x, puntoMasLejano.y); line(P.x,P.y,Q.x,Q.y)";
  } else {
    codigo = "stroke(255, 0, 255); line(P.x, P.y, puntoMasLejano.x, puntoMasLejano.y); line(Q.x, Q.y, puntoMasLejano.x, puntoMasLejano.y); line(P.x,P.y,Q.x,Q.y)";
  }
  let funcion = new Function('P', 'Q', 'puntoMasLejano', codigo);
  pasos.push(() => funcion(P, Q, puntoMasLejano));
}
 
function encontrarHull(segmento, P, Q, tipo) {
  // segmento: conjunto de puntos
  // recta PQ
  // tipo indica si el segmento esta por arriba de la recta (0) o por debajo (1)
  let convexHull = [];
  if (segmento != undefined && segmento.length != 0 && P != undefined && Q != undefined) {
    let distanciaMasLejana = -1;
    let puntoMasLejano;
    for (let point of segmento) {
      let distanciaActual = calcularDistancia(P,Q,point);
      if (distanciaActual > distanciaMasLejana) {
        distanciaMasLejana = distanciaActual;
        puntoMasLejano = point;
      }
    }
    crearFuncion(P, Q, puntoMasLejano, tipo);
    convexHull.push(puntoMasLejano);
    let index = segmento.indexOf(puntoMasLejano)
    segmento.splice(index, 1);
    
    let segmentosP = segmentar(segmento, P, puntoMasLejano);
    let segmentosQ = segmentar(segmento, puntoMasLejano, Q);
    let P_arriba = segmentosP[0];
    let P_abajo = segmentosP[1];
    let Q_arriba = segmentosQ[0];
    let Q_abajo = segmentosQ[1];
    
    if (tipo == '0') {
      convexHull = convexHull.concat(encontrarHull(P_arriba, P, puntoMasLejano, '0'));
      convexHull = convexHull.concat(encontrarHull(Q_arriba, puntoMasLejano, Q, '0'));
    } else {
      convexHull = convexHull.concat(encontrarHull(P_abajo, P, puntoMasLejano, '1'));
      convexHull = convexHull.concat(encontrarHull(Q_abajo, puntoMasLejano, Q, '1'));
    }
  }
  return convexHull;
}

function segmentar(S, P, Q) {
  let segmentos = [];
  if (P != undefined && Q != undefined) {
    let arriba = [];
    let abajo = [];
    segmentos[0] = arriba;
    segmentos[1] = abajo;

    if ((Q.x - P.x) != 0) {
      // recta forma y = mx + b
      let m = (Q.y - P.y)/(Q.x - P.x);
      let b = -m * P.x + P.y;

      for (let point of S) {
        if (point.y > m * point.x + b) {
          arriba.push(point);
        } else if (point.y < m * point.x + b) {
          abajo.push(point);
        }
      }
    }
  }
  return segmentos;
}

function calcularDistancia(P,Q,A) {
  // distancia de un punto A a recta PQ
  let rectaX = Q.x - P.x;
  let rectaY = Q.y - P.y;
  let normalX = -rectaY;
  let normalY = rectaX;
  let vectorX = A.x - P.x;
  let vectorY = A.y - P.y;
  
  return abs(vectorX*normalX + vectorY*normalY)/Math.sqrt(normalX*normalX + normalY*normalY);
}