/*jslint */
/*global AdobeEdge: false, window: false, document: false, console:false, alert: false */
(function (compId) {

    "use strict";
    var im='/adminlte/plugins/an_login/images/',
        aud='media/',
        vid='media/',
        js='js/',
        fonts = {
        },
        opts = {
            'gAudioPreloadPreference': 'auto',
            'gVideoPreloadPreference': 'auto'
        },
        resources = [
        ],
        scripts = [
        ],
        symbols = {
            "stage": {
                version: "6.0.0",
                minimumCompatibleVersion: "5.0.0",
                build: "6.0.0.400",
                scaleToFit: "width",
                centerStage: "none",
                resizeInstances: false,
                content: {
                    dom: [
                        {
                            id: 'Pasted6',
                            type: 'image',
                            rect: ['0px', '72px', '1265px', '328px', 'auto', 'auto'],
                            opacity: '0.84552845528455',
                            fill: ["rgba(0,0,0,0)",im+"Pasted6.svg",'0px','0px'],
                            filter: [0, 0, 1, 1.2328767123287, 0, 0, 0, 0, "rgba(0,0,0,0)", 0, 0, 0]
                        },
                        {
                            id: 'Pasted7',
                            type: 'image',
                            rect: ['-265px', '72px', '1265px', '328px', 'auto', 'auto'],
                            opacity: '0.84552845528455',
                            fill: ["rgba(0,0,0,0)",im+"Pasted7.svg",'0px','0px'],
                            filter: [0, 0, 1, 1.2328767123287, 0, 0, 0, 0, "rgba(0,0,0,0)", 0, 0, 0]
                        }
                    ],
                    style: {
                        '${Stage}': {
                            isStage: true,
                            rect: [undefined, undefined, '1000px', '400px'],
                            sizeRange: ['0px','','',''],
                            overflow: 'hidden',
                            fill: ["rgba(255,255,255,1)"]
                        }
                    }
                },
                timeline: {
                    duration: 5000,
                    autoPlay: true,
                    data: [
                        [
                            "eid145",
                            "opacity",
                            5000,
                            0,
                            "linear",
                            "${Pasted7}",
                            '0.84552845528455',
                            '0.84552845528455'
                        ],
                        [
                            "eid140",
                            "left",
                            0,
                            5000,
                            "swing",
                            "${Pasted7}",
                            '-265px',
                            '0px'
                        ],
                        [
                            "eid156",
                            "top",
                            0,
                            1000,
                            "swing",
                            "${Pasted6}",
                            '72px',
                            '98px'
                        ],
                        [
                            "eid153",
                            "top",
                            1000,
                            1000,
                            "swing",
                            "${Pasted6}",
                            '98px',
                            '72px'
                        ],
                        [
                            "eid154",
                            "top",
                            2000,
                            1000,
                            "swing",
                            "${Pasted6}",
                            '72px',
                            '85px'
                        ],
                        [
                            "eid155",
                            "top",
                            3000,
                            2000,
                            "swing",
                            "${Pasted6}",
                            '85px',
                            '72px'
                        ],
                        [
                            "eid143",
                            "filter.saturate",
                            5000,
                            0,
                            "linear",
                            "${Pasted7}",
                            '1.2328767123287',
                            '1.2328767123287'
                        ],
                        [
                            "eid146",
                            "opacity",
                            5000,
                            0,
                            "linear",
                            "${Pasted6}",
                            '0.84552845528455',
                            '0.84552845528455'
                        ],
                        [
                            "eid138",
                            "left",
                            0,
                            5000,
                            "swing",
                            "${Pasted6}",
                            '0px',
                            '-265px'
                        ],
                        [
                            "eid144",
                            "filter.saturate",
                            5000,
                            0,
                            "linear",
                            "${Pasted6}",
                            '1.2328767123287',
                            '1.2328767123287'
                        ],
                        [
                            "eid174",
                            "top",
                            0,
                            1000,
                            "swing",
                            "${Pasted7}",
                            '72px',
                            '73px'
                        ],
                        [
                            "eid175",
                            "top",
                            1000,
                            1000,
                            "swing",
                            "${Pasted7}",
                            '73px',
                            '72px'
                        ],
                        [
                            "eid176",
                            "top",
                            3000,
                            0,
                            "swing",
                            "${Pasted7}",
                            '72px',
                            '72px'
                        ],
                        [
                            "eid177",
                            "top",
                            5000,
                            0,
                            "swing",
                            "${Pasted7}",
                            '72px',
                            '72px'
                        ]
                    ]
                }
            }
        };

    AdobeEdge.registerCompositionDefn(compId, symbols, fonts, scripts, resources, opts);

    if (!window.edge_authoring_mode) AdobeEdge.getComposition(compId).load("/adminlte/plugins/an_login/edge_includes/_edgeActions.js");
})("EDGE-279555267");
