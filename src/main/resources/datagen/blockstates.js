var fs = require('fs');

let list = require('./list.json');
let list_models = require('./list_models.json');

let template = {
	variants: {
		'type=north': {
			model: 'betterminecraft:block/str_vertical_slab',
			y: 0,
			uvlock: true,
		},
		'type=south': {
			model: 'betterminecraft:block/str_vertical_slab',
			y: 180,
			uvlock: true,
		},
		'type=east': {
			model: 'betterminecraft:block/str_vertical_slab',
			y: 90,
			uvlock: true,
		},
		'type=west': {
			model: 'betterminecraft:block/str_vertical_slab',
			y: 270,
			uvlock: true,
		},
		'type=double': { model: 'minecraft:block/%mod' },
	},
};
for (str in list) {
	let json = JSON.stringify(template)
		.replace(/str/g, list[str])
		.replace(/%mod/g, list_models[str]);
	console.log(json);
	fs.writeFile(
		`./blockstates/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
