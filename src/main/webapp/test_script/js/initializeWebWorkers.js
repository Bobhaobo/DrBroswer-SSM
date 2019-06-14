// This script will load the WebWorkers and Codecs from unpkg url

function getBlobUrl (url) {
  const baseUrl = window.URL || window.webkitURL;
  const blob = new Blob([`importScripts('${url}')`], { type: 'application/javascript' });

  return baseUrl.createObjectURL(blob);
}

function UrlExists (url) {
  const http = new XMLHttpRequest();

  http.open('HEAD', url, true);//这里之前async是false
  http.send();

  return http.status !== 404;
}

let webWorkerUrl = getBlobUrl('cornerstoneWADOImageLoaderWebWorker.min.js');
let codecsUrl = getBlobUrl('cornerstoneWADOImageLoaderCodecs.js');


if (UrlExists('../AnnoURLjs/cornerstoneWADOImageLoaderWebWorker.min.js')) {
  webWorkerUrl = '../AnnoURLjs/cornerstoneWADOImageLoaderWebWorker.min.js';
}

if (UrlExists('../AnnoURLjs/cornerstoneWADOImageLoaderCodecs.js')) {
  codecsUrl = '../AnnoURLjs/cornerstoneWADOImageLoaderCodecs.js';
}

try {
  window.cornerstoneWADOImageLoader.webWorkerManager.initialize({
    maxWebWorkers: 4,
    startWebWorkersOnDemand: true,
    webWorkerPath: webWorkerUrl,
    webWorkerTaskPaths: [],
    taskConfiguration: {
      decodeTask: {
        loadCodecsOnStartup: true,
        initializeCodecsOnStartup: false,
        codecsPath: codecsUrl,
        usePDFJS: false,
        strict: true
      }
    }
  });
} catch (error) {
  throw new Error('cornerstoneWADOImageLoader is not loaded');
}

