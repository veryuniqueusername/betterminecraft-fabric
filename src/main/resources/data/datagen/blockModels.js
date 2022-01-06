var fs = require('fs');

let list = require('./list.json');
let list_textures = require('./list_textures.json');

let template = {
	parent: 'betterminecraft:block/vertical_slab',
	textures: {
		bottom: 'minecraft:block/%tex',
		top: 'minecraft:block/%tex',
		side: 'minecraft:block/%tex',
	},
};
for (str in list) {
	let json = JSON.stringify(template)
		.replace(/str/g, list[str])
		.replace(/%tex/g, list_textures[str]);
	console.log(json);
	fs.writeFile(
		`./models/block/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
