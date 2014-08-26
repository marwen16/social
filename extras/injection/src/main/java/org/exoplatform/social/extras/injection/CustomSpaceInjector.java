package org.exoplatform.social.extras.injection;

import org.exoplatform.social.core.space.SpaceUtils;
import org.exoplatform.social.core.space.impl.DefaultSpaceApplicationHandler;
import org.exoplatform.social.core.space.model.Space;

import java.util.HashMap;

/**
 * Created by eXo Platform MEA on 26/08/14.
 *
 * @author <a href="mailto:mtrabelsi@exoplatform.com">Marwen Trabelsi</a>
 */
public class CustomSpaceInjector extends AbstractSocialInjector
{
  private final int FLUSH_LIMIT = 4;

  /**
   * .
   */
  private static final String SPACE_CREATOR = "spaceCreator";

  /**
   * .
   */
  private static final String SPACE_NAME = "spaceName";

  /**
   * .
   */
  private static final String USER_PREFIX = "userPrefix";

  /**
   * .
   */
  private static final String SPACE_PREFIX = "spacePrefix";

  @Override
  public void inject(HashMap<String, String> params) throws Exception
  {

    String spaceCreator = params.get(SPACE_CREATOR);
    String spaceName = params.get(SPACE_NAME);
    String userPrefix = params.get(USER_PREFIX);
    String spacePrefix = params.get(SPACE_PREFIX);
    init(userPrefix, spacePrefix);

    try
    {
      Space space = new Space();
      space.setDisplayName(spaceName);
      space.setPrettyName(spaceName);
      space.setGroupId("/spaces/" + space.getPrettyName());
      space.setRegistration(Space.OPEN);
      space.setDescription(lorem.getWords(10));
      space.setType(DefaultSpaceApplicationHandler.NAME);
      space.setVisibility(Space.PRIVATE);
      space.setRegistration(Space.OPEN);
      space.setPriority(Space.INTERMEDIATE_PRIORITY);

      //
      spaceService.createSpace(space, spaceCreator);
      getLog().info("Space " + spaceName + " created by " + spaceCreator);

    } finally
    {
      SpaceUtils.endRequest();
    }
  }
}
