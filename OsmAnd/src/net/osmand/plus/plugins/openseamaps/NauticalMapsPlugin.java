package net.osmand.plus.plugins.openseamaps;

import static net.osmand.aidlapi.OsmAndCustomizationConstants.PLUGIN_NAUTICAL;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import net.osmand.plus.ContextMenuAdapter;
import net.osmand.plus.OsmandApplication;
import net.osmand.plus.R;
import net.osmand.plus.activities.MapActivity;
import net.osmand.plus.dialogs.ConfigureMapMenu;
import net.osmand.plus.plugins.OsmandPlugin;
import net.osmand.plus.render.RendererRegistry;
import net.osmand.plus.settings.backend.ApplicationMode;
import net.osmand.render.RenderingRuleProperty;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class NauticalMapsPlugin extends OsmandPlugin {

	public static final String COMPONENT = "net.osmand.nauticalPlugin";
	public static final String DEPTH_CONTOURS = "depthContours";

	public NauticalMapsPlugin(OsmandApplication app) {
		super(app);
	}

	@Override
	public int getLogoResourceId() {
		return R.drawable.ic_plugin_nautical_map;
	}

	@Override
	public Drawable getAssetResourceImage() {
		return app.getUIUtilities().getIcon(R.drawable.nautical_map);
	}

	@Override
	public boolean isMarketPlugin() {
		return true;
	}

	@Override
	public String getComponentId1() {
		return COMPONENT;
	}

	@Override
	public CharSequence getDescription() {
		return app.getString(net.osmand.plus.R.string.plugin_nautical_descr);
	}

	@Override
	public String getName() {
		return app.getString(net.osmand.plus.R.string.plugin_nautical_name);
	}

	@Override
	public String getHelpFileName() {
		return "feature_articles/nautical-charts.html";
	}

	@Override
	public List<ApplicationMode> getAddedAppModes() {
		return Collections.singletonList(ApplicationMode.BOAT);
	}

	@Override
	public List<String> getRendererNames() {
		return Collections.singletonList(RendererRegistry.NAUTICAL_RENDER);
	}

	@Override
	public List<String> getRouterNames() {
		return Collections.singletonList("boat");
	}

	@Override
	public String getId() {
		return PLUGIN_NAUTICAL;
	}

	@Override
	protected void registerLayerContextMenuActions(@NonNull ContextMenuAdapter menuAdapter, @NonNull MapActivity mapActivity, List<RenderingRuleProperty> customRules) {
		Iterator<RenderingRuleProperty> iterator = customRules.iterator();
		while (iterator.hasNext()){
			RenderingRuleProperty property = iterator.next();
			if (DEPTH_CONTOURS.equals(property.getAttrName())) {
				boolean nightMode = app.getDaynightHelper().isNightModeForMapControls();
				menuAdapter.addItem(ConfigureMapMenu.createRenderingProperty(menuAdapter, mapActivity, R.drawable.ic_action_nautical_depth, property, DEPTH_CONTOURS, nightMode));
				iterator.remove();
			}
		}
	}
}