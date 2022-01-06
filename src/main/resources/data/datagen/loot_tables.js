var fs = require('fs');

let list = require('./list.json');

let template = {
	type: 'minecraft:block',
	pools: [
		{
			rolls: 1,
			entries: [
				{
					type: 'minecraft:item',
					functions: [
						{
							function: 'minecraft:set_count',
							conditions: [
								{
									condition: 'minecraft:block_state_property',
									block: 'betterminecraft:str_vertical_slab',
									properties: {
										type: 'double',
									},
								},
							],
							count: 2,
						},
						{
							function: 'minecraft:explosion_decay',
						},
					],
					name: 'betterminecraft:str_vertical_slab',
				},
			],
		},
	],
};
for (str in list) {
	let json = JSON.stringify(template).replace(/str/g, list[str]);
	console.log(json);
	fs.writeFile(
		`./loot_tables/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
