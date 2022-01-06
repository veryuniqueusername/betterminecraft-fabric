var fs = require('fs');

let list = require('./list.json');

let template = {
	type: 'minecraft:crafting_shapeless',
	ingredients: [
		{
			item: 'minecraft:str_slab',
		},
	],
	result: {
		item: 'betterminecraft:str_vertical_slab',
		count: 1,
	},
};

for (str in list) {
	let json = JSON.stringify(template).replace(/str/g, list[str]);
	console.log(json);
	fs.writeFile(
		`./recipes/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
