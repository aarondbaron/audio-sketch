{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 0.0, 44.0, 1680.0, 911.0 ],
		"bglocked" : 0,
		"defrect" : [ 0.0, 44.0, 1680.0, 911.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 15.0, 15.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"metadata" : [  ],
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Preset for you to adjust volume",
					"linecount" : 2,
					"numoutlets" : 0,
					"id" : "obj-31",
					"patching_rect" : [ 213.0, 99.0, 150.0, 34.0 ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "preset",
					"numoutlets" : 4,
					"id" : "obj-169",
					"patching_rect" : [ 211.0, 137.0, 100.0, 40.0 ],
					"outlettype" : [ "preset", "int", "preset", "int" ],
					"numinlets" : 1,
					"preset_data" : [ 						{
							"number" : 1,
							"data" : [ 5, "obj-27", "flonum", "float", 0.0, 5, "obj-11", "flonum", "float", 0.0, 5, "obj-21", "flonum", "float", 0.0, 5, "obj-30", "flonum", "float", 0.0, 5, "obj-44", "flonum", "float", 0.0, 5, "obj-63", "flonum", "float", 0.0, 5, "obj-67", "flonum", "float", 0.0, 5, "obj-71", "flonum", "float", 0.0, 5, "obj-75", "flonum", "float", 0.0, 5, "obj-79", "flonum", "float", 0.0, 5, "obj-83", "flonum", "float", 0.0, 5, "obj-87", "flonum", "float", 0.0, 6, "obj-5", "gain~", "list", 112, 10.0, 6, "obj-6", "gain~", "list", 109, 10.0, 6, "obj-7", "gain~", "list", 115, 10.0, 6, "obj-8", "gain~", "list", 132, 10.0, 6, "obj-16", "gain~", "list", 112, 10.0, 6, "obj-19", "gain~", "list", 100, 10.0, 6, "obj-47", "gain~", "list", 112, 10.0, 6, "obj-48", "gain~", "list", 112, 10.0, 6, "obj-49", "gain~", "list", 99, 10.0, 6, "obj-55", "gain~", "list", 100, 10.0, 6, "obj-56", "gain~", "list", 115, 10.0, 6, "obj-57", "gain~", "list", 110, 10.0, 5, "obj-155", "flonum", "float", 115.0, 5, "obj-156", "flonum", "float", 113.0, 5, "obj-157", "flonum", "float", 117.0, 5, "obj-158", "flonum", "float", 133.0, 5, "obj-159", "flonum", "float", 112.0, 5, "obj-160", "flonum", "float", 102.0, 5, "obj-161", "flonum", "float", 112.0, 5, "obj-162", "flonum", "float", 112.0, 5, "obj-163", "flonum", "float", 99.0, 5, "obj-164", "flonum", "float", 100.0, 5, "obj-165", "flonum", "float", 111.0, 5, "obj-166", "flonum", "float", 107.0 ]
						}
 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port 57848",
					"numoutlets" : 1,
					"id" : "obj-168",
					"patching_rect" : [ 527.0, 317.0, 68.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port 57850",
					"numoutlets" : 1,
					"id" : "obj-167",
					"patching_rect" : [ 527.0, 294.0, 68.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-166",
					"patching_rect" : [ 1369.0, 699.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-165",
					"patching_rect" : [ 1258.0, 698.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-164",
					"patching_rect" : [ 1156.0, 702.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-163",
					"patching_rect" : [ 1041.0, 699.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-162",
					"patching_rect" : [ 927.0, 697.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-161",
					"patching_rect" : [ 828.0, 696.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-160",
					"patching_rect" : [ 711.0, 692.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-159",
					"patching_rect" : [ 599.0, 689.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-158",
					"patching_rect" : [ 490.0, 689.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-157",
					"patching_rect" : [ 384.0, 686.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-156",
					"patching_rect" : [ 265.0, 688.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-155",
					"patching_rect" : [ 146.0, 692.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b s",
					"numoutlets" : 2,
					"id" : "obj-153",
					"patching_rect" : [ 416.0, 233.0, 33.0, 20.0 ],
					"outlettype" : [ "bang", "" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-152",
					"patching_rect" : [ 418.0, 301.0, 90.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "append 1",
					"numoutlets" : 1,
					"id" : "obj-150",
					"patching_rect" : [ 418.0, 329.0, 61.0, 20.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "ZAP",
					"numoutlets" : 1,
					"id" : "obj-149",
					"patching_rect" : [ 550.0, 191.0, 34.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "VIOLA",
					"numoutlets" : 1,
					"id" : "obj-148",
					"patching_rect" : [ 535.0, 176.0, 46.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "TOY_PIANO",
					"numoutlets" : 1,
					"id" : "obj-147",
					"patching_rect" : [ 520.0, 161.0, 79.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "FEMALE",
					"numoutlets" : 1,
					"id" : "obj-146",
					"patching_rect" : [ 505.0, 146.0, 59.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "SAX",
					"numoutlets" : 1,
					"id" : "obj-145",
					"patching_rect" : [ 490.0, 131.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "POP",
					"numoutlets" : 1,
					"id" : "obj-144",
					"patching_rect" : [ 475.0, 116.0, 36.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "CLARINET",
					"numoutlets" : 1,
					"id" : "obj-61",
					"patching_rect" : [ 460.0, 101.0, 70.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "GUITAR_CLASSIC",
					"numoutlets" : 1,
					"id" : "obj-60",
					"patching_rect" : [ 445.0, 86.0, 114.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "GUITAR_ELEC",
					"numoutlets" : 1,
					"id" : "obj-59",
					"patching_rect" : [ 430.0, 71.0, 94.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "CHING",
					"numoutlets" : 1,
					"id" : "obj-58",
					"patching_rect" : [ 415.0, 56.0, 49.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "BANJO",
					"numoutlets" : 1,
					"id" : "obj-40",
					"patching_rect" : [ 400.0, 37.0, 51.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "BUZZ",
					"numoutlets" : 1,
					"id" : "obj-4",
					"patching_rect" : [ 385.0, 22.0, 42.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-57",
					"patching_rect" : [ 1358.0, 621.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-56",
					"patching_rect" : [ 1247.0, 616.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-55",
					"patching_rect" : [ 1143.0, 623.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-49",
					"patching_rect" : [ 1025.0, 623.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-48",
					"patching_rect" : [ 913.0, 622.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-47",
					"patching_rect" : [ 805.0, 621.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-19",
					"patching_rect" : [ 688.0, 619.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-16",
					"patching_rect" : [ 578.0, 617.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-8",
					"patching_rect" : [ 463.0, 616.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-7",
					"patching_rect" : [ 345.0, 615.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-6",
					"patching_rect" : [ 243.0, 616.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "gain~",
					"numoutlets" : 2,
					"id" : "obj-5",
					"patching_rect" : [ 135.0, 616.0, 21.0, 71.0 ],
					"outlettype" : [ "signal", "int" ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"numoutlets" : 1,
					"id" : "obj-143",
					"patching_rect" : [ 48.0, 786.0, 60.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-138",
					"patching_rect" : [ 1496.0, 999.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "zap.wav",
					"numoutlets" : 1,
					"id" : "obj-139",
					"patching_rect" : [ 1428.0, 853.0, 55.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-140",
					"patching_rect" : [ 1427.0, 917.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-141",
					"patching_rect" : [ 1427.0, 945.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ ZAP",
					"numoutlets" : 2,
					"id" : "obj-142",
					"patching_rect" : [ 1427.0, 971.0, 75.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-133",
					"patching_rect" : [ 1370.0, 999.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "viola-harmonic-gliss-Eb.wav",
					"numoutlets" : 1,
					"id" : "obj-134",
					"patching_rect" : [ 1302.0, 853.0, 160.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-135",
					"patching_rect" : [ 1301.0, 917.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-136",
					"patching_rect" : [ 1301.0, 945.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ VIOLA",
					"numoutlets" : 2,
					"id" : "obj-137",
					"patching_rect" : [ 1301.0, 971.0, 87.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-128",
					"patching_rect" : [ 1244.0, 1003.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "toy-piano-Eb-hi.wav",
					"numoutlets" : 1,
					"id" : "obj-129",
					"patching_rect" : [ 1176.0, 857.0, 117.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-130",
					"patching_rect" : [ 1175.0, 921.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-131",
					"patching_rect" : [ 1175.0, 949.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ TOY_PIANO",
					"numoutlets" : 2,
					"id" : "obj-132",
					"patching_rect" : [ 1175.0, 975.0, 120.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-123",
					"patching_rect" : [ 1117.0, 1007.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "singing-female1-Eb-hi.wav",
					"numoutlets" : 1,
					"id" : "obj-124",
					"patching_rect" : [ 1049.0, 861.0, 152.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-125",
					"patching_rect" : [ 1048.0, 925.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-126",
					"patching_rect" : [ 1048.0, 953.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ FEMALE",
					"numoutlets" : 2,
					"id" : "obj-127",
					"patching_rect" : [ 1048.0, 979.0, 99.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-118",
					"patching_rect" : [ 994.0, 1003.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "saxophone-alto-Eb-octave0.wav",
					"numoutlets" : 1,
					"id" : "obj-119",
					"patching_rect" : [ 926.0, 857.0, 182.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-120",
					"patching_rect" : [ 925.0, 921.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-121",
					"patching_rect" : [ 925.0, 949.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ SAX",
					"numoutlets" : 2,
					"id" : "obj-122",
					"patching_rect" : [ 925.0, 975.0, 75.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-113",
					"patching_rect" : [ 868.0, 1004.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "pop.wav",
					"numoutlets" : 1,
					"id" : "obj-114",
					"patching_rect" : [ 800.0, 858.0, 55.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-115",
					"patching_rect" : [ 799.0, 922.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-116",
					"patching_rect" : [ 799.0, 950.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ POP",
					"numoutlets" : 2,
					"id" : "obj-117",
					"patching_rect" : [ 799.0, 976.0, 77.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-108",
					"patching_rect" : [ 744.0, 1001.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "clarinet-Eb-octave2.wav",
					"numoutlets" : 1,
					"id" : "obj-109",
					"patching_rect" : [ 675.0, 871.0, 139.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-110",
					"patching_rect" : [ 675.0, 919.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-111",
					"patching_rect" : [ 675.0, 947.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ CLARINET",
					"numoutlets" : 2,
					"id" : "obj-112",
					"patching_rect" : [ 675.0, 973.0, 111.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-103",
					"patching_rect" : [ 618.0, 1037.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "guitar-classical-Eb-octave3.wav",
					"numoutlets" : 1,
					"id" : "obj-104",
					"patching_rect" : [ 550.0, 894.0, 179.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-105",
					"patching_rect" : [ 549.0, 917.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-106",
					"patching_rect" : [ 549.0, 945.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ GUITAR_CLASSIC",
					"numoutlets" : 2,
					"id" : "obj-107",
					"patching_rect" : [ 549.0, 1008.0, 155.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-98",
					"patching_rect" : [ 493.0, 999.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "guitar-elec-solid-dist-Eb-octave1-long.wav",
					"numoutlets" : 1,
					"id" : "obj-99",
					"patching_rect" : [ 423.0, 874.0, 234.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-100",
					"patching_rect" : [ 424.0, 917.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-101",
					"patching_rect" : [ 424.0, 945.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ GUITAR_ELEC",
					"numoutlets" : 2,
					"id" : "obj-102",
					"patching_rect" : [ 425.0, 971.0, 135.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-93",
					"patching_rect" : [ 366.0, 998.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "ching.wav",
					"numoutlets" : 1,
					"id" : "obj-94",
					"patching_rect" : [ 298.0, 852.0, 64.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-95",
					"patching_rect" : [ 297.0, 916.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-96",
					"patching_rect" : [ 297.0, 944.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ CHING",
					"numoutlets" : 2,
					"id" : "obj-97",
					"patching_rect" : [ 297.0, 969.0, 90.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b b b b b b b b b b b b",
					"numoutlets" : 12,
					"id" : "obj-92",
					"patching_rect" : [ 48.0, 817.0, 1398.5, 20.0 ],
					"outlettype" : [ "bang", "bang", "bang", "bang", "bang", "bang", "bang", "bang", "bang", "bang", "bang", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send trigger",
					"numoutlets" : 0,
					"id" : "obj-91",
					"patching_rect" : [ 680.0, 345.0, 74.0, 20.0 ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive trigger",
					"numoutlets" : 1,
					"id" : "obj-90",
					"patching_rect" : [ 135.0, 373.0, 87.0, 20.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-86",
					"patching_rect" : [ 1357.0, 494.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-87",
					"patching_rect" : [ 1357.0, 520.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-88",
					"patching_rect" : [ 1430.0, 507.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ ZAP",
					"numoutlets" : 2,
					"id" : "obj-89",
					"patching_rect" : [ 1357.0, 552.0, 81.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-82",
					"patching_rect" : [ 1247.0, 494.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-83",
					"patching_rect" : [ 1247.0, 520.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-84",
					"patching_rect" : [ 1320.0, 507.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ VIOLA",
					"numoutlets" : 2,
					"id" : "obj-85",
					"patching_rect" : [ 1247.0, 552.0, 93.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-78",
					"patching_rect" : [ 1138.0, 493.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-79",
					"patching_rect" : [ 1138.0, 519.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-80",
					"patching_rect" : [ 1211.0, 506.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ TOY_PIANO",
					"numoutlets" : 2,
					"id" : "obj-81",
					"patching_rect" : [ 1138.0, 551.0, 126.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-74",
					"patching_rect" : [ 1025.0, 492.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-75",
					"patching_rect" : [ 1025.0, 518.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-76",
					"patching_rect" : [ 1098.0, 505.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ FEMALE",
					"numoutlets" : 2,
					"id" : "obj-77",
					"patching_rect" : [ 1025.0, 550.0, 106.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-70",
					"patching_rect" : [ 910.0, 492.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-71",
					"patching_rect" : [ 910.0, 518.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-72",
					"patching_rect" : [ 983.0, 505.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ SAX",
					"numoutlets" : 2,
					"id" : "obj-73",
					"patching_rect" : [ 910.0, 550.0, 82.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-66",
					"patching_rect" : [ 797.0, 489.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-67",
					"patching_rect" : [ 797.0, 515.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-68",
					"patching_rect" : [ 870.0, 502.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ POP",
					"numoutlets" : 2,
					"id" : "obj-69",
					"patching_rect" : [ 797.0, 547.0, 83.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-62",
					"patching_rect" : [ 686.0, 487.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-63",
					"patching_rect" : [ 686.0, 513.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-64",
					"patching_rect" : [ 759.0, 500.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ CLARINET",
					"numoutlets" : 2,
					"id" : "obj-65",
					"patching_rect" : [ 688.0, 593.0, 117.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-41",
					"patching_rect" : [ 574.0, 487.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-44",
					"patching_rect" : [ 574.0, 513.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-45",
					"patching_rect" : [ 647.0, 500.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ GUITAR_CLASSIC",
					"numoutlets" : 2,
					"id" : "obj-46",
					"patching_rect" : [ 576.0, 569.0, 161.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-28",
					"patching_rect" : [ 461.0, 487.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-30",
					"patching_rect" : [ 461.0, 513.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-35",
					"patching_rect" : [ 534.0, 500.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ GUITAR_ELEC",
					"numoutlets" : 2,
					"id" : "obj-36",
					"patching_rect" : [ 462.0, 547.0, 141.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-18",
					"patching_rect" : [ 351.0, 487.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-21",
					"patching_rect" : [ 351.0, 513.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-23",
					"patching_rect" : [ 424.0, 500.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ CHING",
					"numoutlets" : 2,
					"id" : "obj-25",
					"patching_rect" : [ 351.0, 545.0, 96.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-9",
					"patching_rect" : [ 242.0, 487.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-11",
					"patching_rect" : [ 242.0, 513.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-12",
					"patching_rect" : [ 315.0, 500.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ BANJO",
					"numoutlets" : 2,
					"id" : "obj-17",
					"patching_rect" : [ 242.0, 545.0, 98.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route BUZZ BANJO CHING GUITAR_ELEC GUITAR_CLASSIC CLARINET POP SAX FEMALE TOY_PIANO VIOLA ZAP",
					"numoutlets" : 13,
					"id" : "obj-43",
					"patching_rect" : [ 135.0, 400.0, 1235.0, 20.0 ],
					"outlettype" : [ "", "", "", "", "", "", "", "", "", "", "", "", "" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-26",
					"patching_rect" : [ 134.0, 486.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "flonum",
					"numoutlets" : 2,
					"id" : "obj-27",
					"patching_rect" : [ 134.0, 512.0, 50.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sig~",
					"numoutlets" : 1,
					"id" : "obj-24",
					"patching_rect" : [ 207.0, 499.0, 33.0, 20.0 ],
					"outlettype" : [ "signal" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "ezdac~",
					"numoutlets" : 0,
					"id" : "obj-22",
					"patching_rect" : [ 619.0, 759.0, 45.0, 45.0 ],
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "groove~ BUZZ",
					"numoutlets" : 2,
					"id" : "obj-20",
					"patching_rect" : [ 134.0, 544.0, 89.0, 20.0 ],
					"outlettype" : [ "signal", "signal" ],
					"fontname" : "Arial",
					"numinlets" : 3,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-50",
					"patching_rect" : [ 239.0, 996.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "banjo-Eb-hi.wav",
					"numoutlets" : 1,
					"id" : "obj-51",
					"patching_rect" : [ 171.0, 850.0, 97.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-52",
					"patching_rect" : [ 170.0, 914.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-53",
					"patching_rect" : [ 170.0, 942.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ BANJO",
					"numoutlets" : 2,
					"id" : "obj-54",
					"patching_rect" : [ 170.0, 968.0, 92.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/trigger",
					"numoutlets" : 1,
					"id" : "obj-33",
					"patching_rect" : [ 417.0, 273.0, 48.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpsend localhost 57850",
					"numoutlets" : 0,
					"id" : "obj-32",
					"patching_rect" : [ 418.0, 363.0, 144.0, 20.0 ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /file /trigger",
					"numoutlets" : 3,
					"id" : "obj-37",
					"patching_rect" : [ 640.0, 316.0, 98.0, 20.0 ],
					"outlettype" : [ "", "", "" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 11.595187
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpreceive 57850",
					"numoutlets" : 1,
					"id" : "obj-29",
					"patching_rect" : [ 640.0, 288.0, 106.0, 20.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"id" : "obj-15",
					"patching_rect" : [ 116.0, 995.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "buzz.wav",
					"numoutlets" : 1,
					"id" : "obj-14",
					"patching_rect" : [ 48.0, 849.0, 61.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "read",
					"numoutlets" : 1,
					"id" : "obj-13",
					"patching_rect" : [ 47.0, 913.0, 35.0, 18.0 ],
					"outlettype" : [ "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl join",
					"numoutlets" : 2,
					"id" : "obj-10",
					"patching_rect" : [ 47.0, 941.0, 49.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"fontname" : "Arial",
					"numinlets" : 2,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "buffer~ BUZZ",
					"numoutlets" : 2,
					"id" : "obj-2",
					"patching_rect" : [ 47.0, 967.0, 83.0, 20.0 ],
					"outlettype" : [ "float", "bang" ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"fontsize" : 12.0
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-168", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [ 536.5, 355.5, 427.5, 355.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-167", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [ 536.5, 355.0, 427.5, 355.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-57", 1 ],
					"destination" : [ "obj-166", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-56", 1 ],
					"destination" : [ "obj-165", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 1 ],
					"destination" : [ "obj-164", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 1 ],
					"destination" : [ "obj-163", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-48", 1 ],
					"destination" : [ "obj-162", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-47", 1 ],
					"destination" : [ "obj-161", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 1 ],
					"destination" : [ "obj-160", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 1 ],
					"destination" : [ "obj-159", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 1 ],
					"destination" : [ "obj-158", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 1 ],
					"destination" : [ "obj-157", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 1 ],
					"destination" : [ "obj-156", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 1 ],
					"destination" : [ "obj-155", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-69", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-73", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-77", 0 ],
					"destination" : [ "obj-49", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-81", 0 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-85", 0 ],
					"destination" : [ "obj-56", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-89", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-56", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1255.5, 722.5, 654.5, 722.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-37", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-150", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 394.5, 136.0, 425.5, 136.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 409.5, 143.5, 425.5, 143.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-152", 0 ],
					"destination" : [ "obj-150", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 424.5, 153.0, 425.5, 153.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 439.5, 160.5, 425.5, 160.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 454.5, 168.0, 425.5, 168.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 469.5, 175.5, 425.5, 175.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-144", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 484.5, 183.0, 425.5, 183.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-145", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 499.5, 190.5, 425.5, 190.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-146", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 514.5, 198.0, 425.5, 198.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-147", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 529.5, 205.5, 425.5, 205.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-148", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 544.5, 213.0, 425.5, 213.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-149", 0 ],
					"destination" : [ "obj-153", 0 ],
					"hidden" : 0,
					"midpoints" : [ 559.5, 220.5, 425.5, 220.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-33", 0 ],
					"destination" : [ "obj-152", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-153", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-153", 1 ],
					"destination" : [ "obj-152", 1 ],
					"hidden" : 0,
					"midpoints" : [ 439.5, 266.5, 498.5, 266.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [ 251.5, 717.5, 628.5, 717.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [ 143.5, 738.5, 628.5, 738.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-142", 1 ],
					"destination" : [ "obj-138", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-141", 0 ],
					"destination" : [ "obj-142", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-136", 0 ],
					"destination" : [ "obj-137", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-137", 1 ],
					"destination" : [ "obj-133", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-131", 0 ],
					"destination" : [ "obj-132", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-132", 1 ],
					"destination" : [ "obj-128", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-126", 0 ],
					"destination" : [ "obj-127", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-127", 1 ],
					"destination" : [ "obj-123", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-121", 0 ],
					"destination" : [ "obj-122", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-122", 1 ],
					"destination" : [ "obj-118", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-116", 0 ],
					"destination" : [ "obj-117", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-117", 1 ],
					"destination" : [ "obj-113", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-52", 0 ],
					"destination" : [ "obj-53", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-53", 1 ],
					"hidden" : 0,
					"midpoints" : [ 180.5, 897.5, 209.5, 897.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-10", 1 ],
					"hidden" : 0,
					"midpoints" : [ 57.5, 896.5, 86.5, 896.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [ 144.5, 452.0, 143.5, 452.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-24", 0 ],
					"hidden" : 0,
					"midpoints" : [ 144.5, 458.5, 216.5, 458.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-62", 0 ],
					"destination" : [ "obj-63", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-66", 0 ],
					"destination" : [ "obj-67", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-70", 0 ],
					"destination" : [ "obj-71", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-75", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-78", 0 ],
					"destination" : [ "obj-79", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-82", 0 ],
					"destination" : [ "obj-83", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-86", 0 ],
					"destination" : [ "obj-87", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 1 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [ 245.833328, 452.5, 251.5, 452.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 1 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [ 245.833328, 459.0, 324.5, 459.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 2 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [ 347.166656, 452.5, 360.5, 452.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 2 ],
					"destination" : [ "obj-23", 0 ],
					"hidden" : 0,
					"midpoints" : [ 347.166656, 459.0, 433.5, 459.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 3 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [ 448.5, 452.5, 470.5, 452.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 3 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [ 448.5, 459.0, 543.5, 459.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 4 ],
					"destination" : [ "obj-41", 0 ],
					"hidden" : 0,
					"midpoints" : [ 549.833313, 452.5, 583.5, 452.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 4 ],
					"destination" : [ "obj-45", 0 ],
					"hidden" : 0,
					"midpoints" : [ 549.833313, 459.0, 656.5, 459.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 5 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [ 651.166687, 452.5, 695.5, 452.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 5 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [ 651.166687, 459.5, 768.5, 459.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 6 ],
					"destination" : [ "obj-66", 0 ],
					"hidden" : 0,
					"midpoints" : [ 752.5, 454.0, 806.5, 454.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 6 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [ 752.5, 460.5, 879.5, 460.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 7 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [ 853.833313, 455.5, 919.5, 455.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 7 ],
					"destination" : [ "obj-72", 0 ],
					"hidden" : 0,
					"midpoints" : [ 853.833313, 462.0, 992.5, 462.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 8 ],
					"destination" : [ "obj-74", 0 ],
					"hidden" : 0,
					"midpoints" : [ 955.166687, 455.5, 1034.5, 455.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 8 ],
					"destination" : [ "obj-76", 0 ],
					"hidden" : 0,
					"midpoints" : [ 955.166687, 462.0, 1107.5, 462.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 9 ],
					"destination" : [ "obj-78", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1056.5, 456.0, 1147.5, 456.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 9 ],
					"destination" : [ "obj-80", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1056.5, 462.5, 1220.5, 462.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 10 ],
					"destination" : [ "obj-82", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1157.833374, 456.5, 1256.5, 456.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 10 ],
					"destination" : [ "obj-84", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1157.833374, 463.0, 1329.5, 463.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 11 ],
					"destination" : [ "obj-86", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1259.166626, 456.5, 1366.5, 456.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 11 ],
					"destination" : [ "obj-88", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1259.166626, 463.0, 1439.5, 463.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-90", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 1 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-94", 0 ],
					"destination" : [ "obj-95", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-94", 0 ],
					"destination" : [ "obj-96", 1 ],
					"hidden" : 0,
					"midpoints" : [ 307.5, 899.5, 336.5, 899.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-95", 0 ],
					"destination" : [ "obj-96", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 2 ],
					"destination" : [ "obj-94", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-100", 0 ],
					"destination" : [ "obj-101", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-99", 0 ],
					"destination" : [ "obj-101", 1 ],
					"hidden" : 0,
					"midpoints" : [ 432.5, 900.5, 463.5, 900.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-99", 0 ],
					"destination" : [ "obj-100", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 3 ],
					"destination" : [ "obj-99", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-104", 0 ],
					"destination" : [ "obj-105", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-104", 0 ],
					"destination" : [ "obj-106", 1 ],
					"hidden" : 0,
					"midpoints" : [ 559.5, 900.5, 588.5, 900.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-105", 0 ],
					"destination" : [ "obj-106", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 4 ],
					"destination" : [ "obj-104", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-110", 0 ],
					"destination" : [ "obj-111", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-109", 0 ],
					"destination" : [ "obj-111", 1 ],
					"hidden" : 0,
					"midpoints" : [ 684.5, 902.5, 714.5, 902.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-109", 0 ],
					"destination" : [ "obj-110", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 5 ],
					"destination" : [ "obj-109", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-114", 0 ],
					"destination" : [ "obj-115", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-114", 0 ],
					"destination" : [ "obj-116", 1 ],
					"hidden" : 0,
					"midpoints" : [ 809.5, 905.5, 838.5, 905.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-115", 0 ],
					"destination" : [ "obj-116", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 6 ],
					"destination" : [ "obj-114", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-119", 0 ],
					"destination" : [ "obj-120", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-119", 0 ],
					"destination" : [ "obj-121", 1 ],
					"hidden" : 0,
					"midpoints" : [ 935.5, 904.5, 964.5, 904.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-120", 0 ],
					"destination" : [ "obj-121", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 7 ],
					"destination" : [ "obj-119", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-124", 0 ],
					"destination" : [ "obj-125", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-124", 0 ],
					"destination" : [ "obj-126", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1058.5, 908.5, 1087.5, 908.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-125", 0 ],
					"destination" : [ "obj-126", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 8 ],
					"destination" : [ "obj-124", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-129", 0 ],
					"destination" : [ "obj-130", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-129", 0 ],
					"destination" : [ "obj-131", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1185.5, 904.5, 1214.5, 904.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-130", 0 ],
					"destination" : [ "obj-131", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 9 ],
					"destination" : [ "obj-129", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-134", 0 ],
					"destination" : [ "obj-135", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-134", 0 ],
					"destination" : [ "obj-136", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1311.5, 900.5, 1340.5, 900.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-135", 0 ],
					"destination" : [ "obj-136", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 10 ],
					"destination" : [ "obj-134", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-140", 0 ],
					"destination" : [ "obj-141", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-139", 0 ],
					"destination" : [ "obj-141", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1437.5, 900.5, 1466.5, 900.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-139", 0 ],
					"destination" : [ "obj-140", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-92", 11 ],
					"destination" : [ "obj-139", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-143", 0 ],
					"destination" : [ "obj-92", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 1 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-53", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-54", 1 ],
					"destination" : [ "obj-50", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-96", 0 ],
					"destination" : [ "obj-97", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-97", 1 ],
					"destination" : [ "obj-93", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-102", 1 ],
					"destination" : [ "obj-98", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-101", 0 ],
					"destination" : [ "obj-102", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-107", 1 ],
					"destination" : [ "obj-103", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-106", 0 ],
					"destination" : [ "obj-107", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-112", 1 ],
					"destination" : [ "obj-108", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-111", 0 ],
					"destination" : [ "obj-112", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-24", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [ 216.5, 538.0, 143.5, 538.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [ 324.5, 539.0, 251.5, 539.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [ 433.5, 539.0, 360.5, 539.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-30", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [ 543.5, 539.0, 471.5, 539.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-45", 0 ],
					"destination" : [ "obj-46", 0 ],
					"hidden" : 0,
					"midpoints" : [ 656.5, 539.0, 585.5, 539.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-46", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-63", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [ 768.5, 539.0, 697.5, 539.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 1 ],
					"destination" : [ "obj-91", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-69", 0 ],
					"hidden" : 0,
					"midpoints" : [ 879.5, 541.0, 806.5, 541.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-67", 0 ],
					"destination" : [ "obj-69", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-73", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-72", 0 ],
					"destination" : [ "obj-73", 0 ],
					"hidden" : 0,
					"midpoints" : [ 992.5, 544.0, 919.5, 544.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-75", 0 ],
					"destination" : [ "obj-77", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-76", 0 ],
					"destination" : [ "obj-77", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1107.5, 544.0, 1034.5, 544.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-80", 0 ],
					"destination" : [ "obj-81", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1220.5, 545.0, 1147.5, 545.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-79", 0 ],
					"destination" : [ "obj-81", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-84", 0 ],
					"destination" : [ "obj-85", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1329.5, 546.0, 1256.5, 546.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-83", 0 ],
					"destination" : [ "obj-85", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-88", 0 ],
					"destination" : [ "obj-89", 0 ],
					"hidden" : 0,
					"midpoints" : [ 1439.5, 546.0, 1366.5, 546.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-87", 0 ],
					"destination" : [ "obj-89", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [ 353.5, 717.5, 628.5, 717.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [ 471.5, 717.5, 628.5, 717.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [ 586.5, 723.0, 628.5, 723.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-47", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 813.5, 725.0, 654.5, 725.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-48", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 921.5, 725.5, 654.5, 725.5 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1033.5, 726.0, 654.5, 726.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1151.5, 726.0, 654.5, 726.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-57", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [ 1366.5, 725.0, 654.5, 725.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
