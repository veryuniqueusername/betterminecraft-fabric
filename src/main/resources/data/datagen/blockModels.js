var fs = require('fs');

let list = require('./list.json');

let template = {
	parent: 'betterminecraft:block/vertical_slab',
	textures: {
		bottom: 'minecraft:block/str',
		top: 'minecraft:block/str',
		side: 'minecraft:block/str',
	},
};
for (str in list) {
	let json = JSON.stringify(template).replace(/str/g, list[str]);
	console.log(json);
	fs.writeFile(
		`./models/block/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
